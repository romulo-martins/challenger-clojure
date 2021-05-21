(ns challenger-clojure.db.seed
  (:use [clojure pprint])
  (:require [challenger-clojure.db.core :as db]
            [challenger-clojure.db.sample :as sample]
            [challenger-clojure.models.customer :as customer]
            [challenger-clojure.models.credit-card :as credit-card]
            [challenger-clojure.models.purchase :as purchase]))

(defn seed-customers! [conn]
  (let [customers (map customer/new sample/customers)]
    (db/insert-data! conn customers)))

(defn seed-credit-cards! [conn]
  (let [credit-cards (map credit-card/new sample/credit-cards)]
    (db/insert-data! conn credit-cards)))

(defn seed-purchases! [conn]
  (let [purchases (map purchase/new sample/purchases)]
    (db/insert-data! conn purchases)))

(defn run
  [conn]
  (println "Seeding database with customers ...")
  (seed-customers! conn)
  (println "Seeding database with credit cards ...")
  (seed-credit-cards! conn)
  (println "Seeding database with purchases ...")
  (seed-purchases! conn)
  (println "Successful seeding!"))
