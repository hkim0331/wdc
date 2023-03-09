#!/usr/bin/env bb
(require
 '[babashka.http-client :as http]
 '[taoensso.timbre :as timbre])

(def version "0.3.2")

(timbre/merge-config!
 {:min-level :debug
  :timestamp-opts
  {:pattern "yyyy-MM-dd HH:mm:ss"
   :timezone :jvm-default}})

(def url (or (System/getenv "WDC_URL") "http://localhost:3000/"))

(def params {"user_id"  (or (System/getenv "WDC_USER") "user_id")
             "password" (or (System/getenv "WDC_PASS") "password")
             "watch"    ""})

(defn wdc [url params]
  (let [resp (http/post url {:form-params params})]
    (if (= 200 (:status resp))
      (timbre/info "success" (params "dakoku"))
      (timbre/error resp))))

;; FIXME: tail
(defn print-log []
  (let [log (str (System/getenv "WDC_DIR") "/log/wdc.log")]
    (println (slurp log))))

(comment
  (print-log)
  :rcf)

(defn -main []
  (doseq [verb *command-line-args*]
    ;; (println "verb" verb)
    (case verb
      "in"  (wdc url (merge params {"dakoku" "syussya"}))
      "out" (wdc url (merge params {"dakoku" "taisya"}))
      "version" (println version)
      "log" (print-log)
      (println "usage: wdc.clj [in | out | log | version]"))))

(-main)
