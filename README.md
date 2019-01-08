A quick demo that illustrates how to use Clojure's [spec](https://clojure.org/guides/spec) to validate objects from Kotlin.

A small namespace help convert mutable Kotlin maps into Clojure's persistent maps before calling `clojure.spec.alpha/keys` on the map.