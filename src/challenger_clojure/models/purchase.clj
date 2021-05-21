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

(defn most-expensive
  [conn]
  (let [highest-amount (ffirst (d/q '[:find (max ?amount)
                                      :where [?purchase :purchase/amount ?amount]]
                                    (d/db conn)))]
    (d/q '[:find (pull ?purchase [* {:purchase/customer [*]}])
           :in $ ?highest-amount
           :where [?purchase :purchase/amount ?amount]
           [(= ?amount ?highest-amount)]] (d/db conn) highest-amount)))

;; (defn total-by-customer
;;   [conn]
;;   (d/q '[:find ?customer-name (count ?purchase-id)
;;          :keys customer-name total-purchases
;;          :with ?purchase
;;          :where [?purchase :purchase/id ?purchase-id]
;;          [?purchase :purchase/customer ?customer]
;;          [?customer :customer/name ?customer-name]] (d/db conn)))
