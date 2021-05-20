(ns challenger-clojure.db.core
  (:require [datomic.api :as d]
            [challenger-clojure.db.schema :as schema]))

(def db-uri "datomic:dev://localhost:4334/challenger_clojure)")

(defn open-connection! []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn insert-data!
  [conn data]
  (d/transact conn data))

(defn delete-database! []
  (d/delete-database db-uri))

(defn create-schema!
  [conn]
  (d/transact conn schema/instance))
