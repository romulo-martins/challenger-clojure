(ns challenger-clojure.models.purchase
  (:require [challenger-clojure.db :as c.db]
            [challenger-clojure.helpers.date-helper :as c.date]))

(defn all [] c.db/purchases)

(defn by-company
  [company]
  (filter #(= company (get % :company)) c.db/purchases))

(defn by-month
  ([date]
   (filter #(c.date/same-month? date (% :date)) c.db/purchases))
  ([date purchases]
   (filter #(c.date/same-month? date (% :date)) purchases)))

(defn by-customer
  ([customer-id]
   (filter #(= customer-id (% :customer-id)) c.db/purchases))
  ([customer-id purchases]
   (filter #(= customer-id (% :customer-id)) purchases)))

; Adiciona um novo elemento e retorna uma nova lista de compras 
(defn add
  [purchases new-purchase]
  (conj purchases new-purchase))


; Total por categoria

(defn- compute-purchases-amount
  [purchases]
  (->>
   purchases
   (map #(get % :amount))
   (reduce +)))

(defn- compute-amount-by-category
  [[category purchases]]
  (if (not (nil? category))
    {:category category
    :total-amount (compute-purchases-amount purchases)}))

(defn total-amount-by-category
  [purchases]
  (->>
   purchases
   (group-by :category)
   (map compute-amount-by-category)
   (remove nil?)))
