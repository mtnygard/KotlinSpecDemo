(ns demo.kotlin-bridge
  (:import (java.util LinkedHashMap)))


(defn what-is [x]
  (println "Container type: " (type x))
  (doseq [[k v] x]
    (println k " (" (type k) ") => " v " (" (type v) ")")))

(defn clojure-map [^LinkedHashMap kotlin-map]
  (into {} kotlin-map))

