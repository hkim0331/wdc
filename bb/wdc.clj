#!/usr/bin/env bb

(require '[babashka.http-client :as http])

(def develop true)
;;
(if develop
  (def url "http://localhost:3000/")
  (def url "https://jinji-w.jimu.kyutech.ac.jp/cws/srwtimerec"))

(def param {"user_id" (System/getenv "WDC_USER")
            "pasword" (System/getenv "WDC_PASSWORD")
            "watch"   ""})

(def in (merge param {"dakoku"  "syussya"}))

(def out (merge param {"dakoku"  "taisya"}))

(defn wdc [params]
  (:body (http/post url {:form-params params})))

(defn wdc-in  [] (wdc in))
(defn wdc-out [] (wdc out))

(comment
  (wdc-in)
  (wdc-out)
  :rcf)
