#!/usr/bin/env bb
(require
 '[babashka.fs :as fs]
 '[babashka.http-client :as http]
 '[clojure.edn :as edn]
 '[clojure.java.io :as io]
 '[taoensso.timbre :as timbre])

(def version "0.4.4")

(timbre/merge-config!
 {:min-level :debug
  :timestamp-opts
  {:pattern "yyyy-MM-dd HH:mm:ss"
   :timezone :jvm-default}})

(defn load-edn
  "Load edn from an io/reader source (filename or io/resource)."
  [source]
  (try
    (with-open [r (io/reader source)]
      (edn/read (java.io.PushbackReader. r)))
    (catch java.io.IOException e
      (timbre/error "Couldn't open" source (.getMessage e)))
    (catch RuntimeException e
      (timbre/error "Error parsing edn file" source (.getMessage e)))))

;; debug config?
(defn load-config [filename]
  (-> filename
      fs/expand-home
      str
      load-edn))

(defn wdc [config]
  (try
    (let [url (:wdc-url config)
          params {"user_id"  (:wdc-user config)
                  "password" (:wdc-pass config)
                  "dakoku"   (:wdc-op   config)
                  "watch"    ""}
          resp (http/post url {:form-params params})]
      (if (= 200 (:status resp))
        (timbre/info "success" (params "dakoku"))
        (timbre/error resp)))
    (catch Exception e
      (timbre/error (.getMessage e))
      ;; retry?
      ;; (Thread/sleep 30000)
      ;; (wdc config)
      )))

(comment
  (defn moop [n]
    (try
      (when (pos? n)
        (throw (Exception. (str "positive " n))))
      (catch Exception e
        (println (.getMessage e))
        (Thread/sleep 1000)
        ;; not recur
        (moop (dec n)))))
  (moop 3)
  :rcf)

;; FIXME: tail
;;        config は引数ではない、か？
(defn print-log [config]
  (let [log (str (fs/expand-home (:wdc-log config)))]
    (println (slurp log))))

(defn -main [& _args]
  (let [config (load-config "~/.config/wdc/wdc.edn")]
    (doseq [verb *command-line-args*]
      (case verb
        "in"  (wdc (merge config {:wdc-op "syussya"}))
        "out" (wdc (merge config {:wdc-op "taisya"}))
        "version" (println version)
        "log" (print-log config)
        (println "usage: wdc.clj [in|out|log|version]")))))

(comment
  (-main "in")
  )

;; FIXME: better way?
(-main)
