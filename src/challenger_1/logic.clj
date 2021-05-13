(ns challenger-1.logic
  (:require [challenger-1.db :as c.db]
            [challenger-1.helpers.search-helper :as c.searcher]))

(defn compute-purchases-amount
  [purchases]
  (->>
   purchases
   (map #(get % :amount))
   (reduce +)))

(defn compute-amount-by-category
  [[category purchases]]
  {:category category
   :total-amount (compute-purchases-amount purchases)})

(defn total-amount-by-category
  [purchases]
  (->>
   purchases
   (group-by :category)
   (map compute-amount-by-category)))

(defn customers-month-invoice
  [customer-id date purchases]
  (->>
   purchases
   (c.searcher/purchases-by-customer customer-id)
   (c.searcher/purchases-by-month date)))

(defn compute-total-amount-invoice
  [customer-id date purchases]
  (->>
   (customers-month-invoice customer-id date purchases)
   (compute-purchases-amount)))

(defn customer-by-id
  [customer-id]
  (filter #(= customer-id (% :id)) c.db/customers))

(defn credit-card-by-customer-id
  [customer-id]
  (filter #(= customer-id (% :customer-id)) c.db/credit-cards))

(defn customer-invoice-by-month
  [customer-id date purchases]
  {:customer (customer-by-id customer-id)
   :credit-card (credit-card-by-customer-id customer-id)
   :purchases (customers-month-invoice customer-id date purchases)
   :total-amount-invoice (compute-total-amount-invoice customer-id date purchases)})