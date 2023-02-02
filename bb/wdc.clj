(ns wdc)
(require '[babashka.http-client :as http])

(def url (or (System/getenv "WDC_URL") "http://localhost:3000/"))

(def params {"user_id" (or (System/getenv "WDC_USER") "user_id")
             "pasword" (or (System/getenv "WDC_PASS") "password")
             "watch"   ""})

(defn wdc [params]
  (:body (http/post url {:form-params params})))

(defn in  [_] (wdc (merge params {"dakoku"  "syussya"})))

(defn out [_] (wdc (merge params {"dakoku"  "taisya"})))

(comment
  ;; in/out requires an argument. dummy.
  (in  0)
  (out 0)
  :rcf)
