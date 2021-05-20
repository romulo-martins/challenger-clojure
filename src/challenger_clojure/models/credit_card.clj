(ns challenger-clojure.models.credit-card
  (:require [datomic.api :as d]
            [challenger-clojure.helpers.uuid-helper :as uuid]))

(defn new
  [params]
  {:credit-card/id          (uuid/random)
   :credit-card/number      (params :number)
   :credit-card/cvv         (params :cvv)
   :credit-card/valid-date  (params :valid-date)
   :credit-card/limit       (params :limit)
   :credit-card/customer    [:customer/id (uuid/from-string (params :customer-id))]})

(defn all
  "Returns all credit cards on database"
  [conn]
  (d/q '[:find (pull ?credit-card [*])
         :where [?credit-card :credit-card/id]] (d/db conn)))
