(ns challenger-clojure.models.purchase
  (:require [datomic.api :as d]
            [challenger-clojure.helpers.uuid-helper :as uuid]
            [challenger-clojure.helpers.date-helper :as date]))

(defn new
  [params]
  {:purchase/id       (uuid/random)
   :purchase/date     (params :date)
   :purchase/amount   (params :amount)
   :purchase/company  (params :company)
   :purchase/category (params :category)
   :purchase/customer [:customer/id (uuid/from-string (params :customer-id))]})

(defn all
  "Returns all purchases on database"
  [conn]
  (d/q '[:find (pull ?purchase [*])
         :where [?purchase :purchase/id]] (d/db conn)))
