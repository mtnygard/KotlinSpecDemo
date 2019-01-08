(ns demo.messages
  (:require [clojure.spec.alpha :as s]))

(s/def ::username string?)
(s/def ::days-ago integer?)
(s/def ::accepted-tos? boolean?)
(s/def ::entity   (s/keys :req-un [::username ::days-ago] :opt-un [::accepted-tos?]))

(println "Loaded namespace " *ns*)
