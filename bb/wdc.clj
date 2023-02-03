#!/usr/bin/env bb
(require '[babashka.http-client :as http]
         '[clojure.tools.logging :as log])

(def url (or (System/getenv "WDC_URL") "http://localhost:3000/"))
(def params {"user_id"  (or (System/getenv "WDC_USER") "user_id")
             "password" (or (System/getenv "WDC_PASS") "password")
             "watch"    ""})

(defn wdc [url params]
  (try
    (log/debug "wdc:url:" url)
    (log/debug "wdc:params:" params)
    (:status (http/post url {:form-params params}))
    (catch Exception e (log/error (.getMessage e)))))

(let [verb (first *command-line-args*)]
  (case verb
    "in"  (wdc url (merge params {"dakoku" "syussya"}))
    "out" (wdc url (merge params {"dakoku" "taisya"}))
    (println "usage: wdc.clj [in|out]")))


;; (defn in  [_] (wdc (merge params )))
;; (defn out [_] (wdc (merge params {"dakoku"  "taisya"})))
;; (comment
;;   ;; in/out requires an argument. dummy.
;;   (in  0)
;;   (out 0)
;;   :rcf)
