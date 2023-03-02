(require '[babashka.http-client :as http]
         '[taoensso.timbre :as timbre])

(timbre/merge-config!
 {:timestamp-opts
  {:pattern "yyyy-MM-dd HH:mm:ss"
   :timezone :jvm-default}})

(def url (or (System/getenv "WDC_URL") "http://localhost:3000/"))

(def params {"user_id"  (or (System/getenv "WDC_USER") "user_id")
             "password" (or (System/getenv "WDC_PASS") "password")
             "watch"    ""})

(defn wdc [url params]
  (try
    ;; こいつを入れたら動き出した。もうちょっとチェックを続ける。
    ;; (log/debug (:status (http/get "https://httpstat.us/200")) "httpstat.us")
    (timbre/debug (:status (http/post url {:form-params params})) url)
    (timbre/info  "wdc success" (params "dakoku"))
    (catch Exception e (timbre/error (.getMessage e)))))

(let [verb (first *command-line-args*)]
  (case verb
    "in"  (wdc url (merge params {"dakoku" "syussya"}))
    "out" (wdc url (merge params {"dakoku" "taisya"}))
    (timbre/warn "usage: wdc.clj [in|out]")))
