(ns challenger-1.logic
  (:require [challenger-1.models.customer :as c.customer]
            [challenger-1.models.credit-card :as c.credit-card]
            [challenger-1.models.purchase :as c.purchase]))

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
  [customer-id date]
  (->>
   (c.purchase/all)
   (c.purchase/by-customer customer-id)
   (c.purchase/by-month date)))

(defn compute-total-amount-invoice
  [customer-id date]
  (->>
   (customers-month-invoice customer-id date)
   (compute-purchases-amount)))

(defn customer-invoice-by-month
  [customer-id date]
  {:customer (c.customer/by-id customer-id)
   :credit-card (c.credit-card/by-customer-id customer-id)
   :purchases (customers-month-invoice customer-id date)
   :total-amount-invoice (compute-total-amount-invoice customer-id date)})