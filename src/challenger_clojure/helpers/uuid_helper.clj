(ns challenger-clojure.helpers.uuid-helper)

(defn random [] (java.util.UUID/randomUUID))

(defn from-string [string] (java.util.UUID/fromString string))
