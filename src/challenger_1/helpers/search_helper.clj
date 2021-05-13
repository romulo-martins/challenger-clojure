(ns challenger-1.helpers.search-helper
    (:require [challenger-1.helpers.date-helper :as c.date]))

(defn purchases-by-company
  [company purchases]
  (filter #(= company (get % :company)) purchases))

(defn purchases-by-month
  [date purchases]
  (filter #(c.date/same-month? date (% :date)) purchases))

(defn purchases-by-customer
  [customer-id purchases]
  (filter #(= customer-id (% :customer-id)) purchases))