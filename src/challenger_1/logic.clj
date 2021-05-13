(ns challenger-1.logic
  (:require [challenger-1.db :as c.db]
            [challenger-1.date-helper :as c.date]
            [clojure.pprint :as pp]))

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

(defn search-purchases-by-company
  [company purchases]
  (filter
   #(= company (get % :company))
   purchases))

;; (println "Clientes:")
;; (pp/pprint c.db/customers)

;; (println "\nCartoes de crédito:")
;; (pp/pprint c.db/credit-cards)

;; (println "\nCompras realizadas:")
;; (pp/pprint c.db/purchases)

;; (println "\nTotal por categoria:")
;; (pp/pprint (total-amount-by-category c.db/purchases))

;; (println "\nBusca por estabelecimento:")
;; (pp/pprint (search-purchases-by-company "Ri Happy" c.db/purchases))

(defn search-purchases-by-month
  [date purchases]
  (filter #(c.date/same-month? date (% :date)) purchases))

(defn search-purchases-by-customer
  [customer-id purchases]
  (filter #(= customer-id (% :customer-id)) purchases))

(defn compute-invoice
  [purchases]
  {:purchases purchases
   :total-amount (compute-purchases-amount purchases)})

(defn customer-invoice-by-month
  [customer-id date purchases]
  (->>
   purchases
   (search-purchases-by-customer customer-id)
   (search-purchases-by-month date)
   (compute-invoice)))

(println "Calculo da fatura do mes: ")
(pp/pprint (customer-invoice-by-month 1 "2020-04" c.db/purchases))
