#!/usr/bin/env bb
(require
 '[babashka.fs :as fs]
 '[babashka.http-client :as http]
 '[clojure.edn :as edn]
 '[clojure.java.io :as io]
 '[taoensso.timbre :as timbre])

(def version "0.3.3")

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

(def config (-> "~/.config/wdc/wdc.edn"
                fs/expand-home
                str
                load-edn))

(defn wdc [url params]
  (let [resp (http/post url {:form-params params})]
    (if (= 200 (:status resp))
      (timbre/info "success" (params "dakoku"))
      (timbre/error resp))))

;; FIXME: tail
(defn print-log []
  (let [log (str (fs/expand-home (:wdc-log config)))]
    (println (slurp log))))

(comment
  (print-log)
  :rcf)

(defn -main []
  (let [url (:wdc-url config)
        params {"user_id"  (:wdc-user config)
                "password" (:wdc-pass config)
                "watch"    ""}]
    (doseq [verb *command-line-args*]
      (case verb
        "in"  (wdc url (merge params {"dakoku" "syussya"}))
        "out" (wdc url (merge params {"dakoku" "taisya"}))
        "version" (println version)
        "log" (print-log)
        (println "usage: wdc.clj [in | out | log | version]")))))

;; FIXME: better way?
(-main)
