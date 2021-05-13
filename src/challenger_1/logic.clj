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

(defn compute-invoice
  [purchases]
  {:purchases purchases
   :total-amount (compute-purchases-amount purchases)})

(defn customer-invoice-by-month
  [customer-id date purchases]
  (->>
   purchases
   (c.searcher/purchases-by-customer customer-id)
   (c.searcher/purchases-by-month date)
   (compute-invoice)))

