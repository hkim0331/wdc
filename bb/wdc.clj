(require '[babashka.http-client :as http]
         '[clojure.tools.logging :as log])

(def url (or (System/getenv "WDC_URL") "http://localhost:3000/"))
(def params {"user_id"  (or (System/getenv "WDC_USER") "user_id")
             "password" (or (System/getenv "WDC_PASS") "password")
             "watch"    ""})

(defn wdc [url params]
  (try
    ;; こいつを入れたら動き出した。もうちょっとチェックを続ける。
    ;; (log/debug (:status (http/get "https://httpstat.us/200")) "httpstat.us")
    (log/debug (:status (http/post url {:form-params params})) url)
    (log/info  "wdc success" (params "dakoku"))
    (catch Exception e (log/error (.getMessage e)))))

(let [verb (first *command-line-args*)]
  (case verb
    "in"  (wdc url (merge params {"dakoku" "syussya"}))
    "out" (wdc url (merge params {"dakoku" "taisya"}))
    (log/warn "usage: wdc.clj [in|out]")))
