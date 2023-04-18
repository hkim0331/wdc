#!/usr/bin/env bb
(require '[babashka.fs :as fs])

;; (defn date-string []
;;   (str (java.time.LocalDate/now)))

(defn time-string []
  (subs (str (java.time.LocalTime/now)) 0 8))

(defn touch-file
  ([dir file]
   (spit (str (babashka.fs/expand-home (str dir file)))
         (str (java.time.LocalDateTime/now)
              "\n"
              (System/getenv "WDC_URL"))))
  ([dir] (touch-file dir (time-string)))
  ([] (touch-file "~/Desktop/" (time-string))))

(comment
  (time-string)
  (touch-file "~/ramdisk/")
  (touch-file)
  (try
    (touch-file "~/Desktop/")
    (catch Exception e (println (.getMessage e))))
  )

(defn -main []
  ;;(println "args:" args)
  (println "*command-line-args*" *command-line-args*)
  (touch-file)
  )

(-main)
