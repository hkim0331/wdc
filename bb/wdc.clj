#!/usr/bin/env bb
(require
 '[babashka.fs :as fs]
 '[babashka.process :as ps]
 '[babashka.http-client :as http]
 '[clojure.edn :as edn]
 '[clojure.java.io :as io]
 '[taoensso.timbre :as timbre])

(def version "0.5.0")

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

;; Added 2023-10-11
;; http --body --session where-is-me ${URL}/ loc="$*"
(def url "w.hkim.jp")

;; FIXME: does not work.
;; (defn w-hkim-jp-send
;;   [loc]
;;   (let [cmd (str "https --ignore-stdin --session where-is me "
;;                  url
;;                  " loc='" loc "'")]
;;     (ps/shell {:out "/dev/null"} cmd)))

;; process/shell は stdin を開いてプロセスにつないでしまうのかな？
;; --ignore-stdin を足さないとエラーになる。
(defn w-hkim-jp
  [s]
  (let [cmd (str "https --ignore-stdin --session where-is-me " url)]
    (case s
      ;; DRY!
      "syussya" (ps/shell {:out "/dev/null"} (str cmd " loc='214着。'"))
      "taisya"  (ps/shell {:out "/dev/null"} (str cmd " loc='帰宅します。'"))
      (throw (Exception. "error: w_hkim_jp")))))

(comment
  (ps/shell (str "https " url))
  (w-hkim-jp "syussya")
  (w-hkim-jp "taisya")
  :rcf)

(defn wdc [config]
  (try
    (let [url (:wdc-url config)
          params {"user_id"  (:wdc-user config)
                  "password" (:wdc-pass config)
                  "dakoku"   (:wdc-op   config)
                  "watch"    ""}
          resp (http/post url {:form-params params})]
      (if (= 200 (:status resp))
        (let [dakoku (params "dakoku")]
          (timbre/info "success" dakoku)
          ;; (println "dakoku" dakoku)
          (w-hkim-jp dakoku))
        (timbre/error resp)))
    (catch Exception e
      (timbre/error (.getMessage e)))))

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
  :rcf)

;; FIXME: better way?
;; clojure -M -m
(-main)
