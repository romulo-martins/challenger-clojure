(ns challenger-clojure.models.purchase
  (:require [challenger-clojure.db :as c.db]
            [challenger-clojure.helpers.date-helper :as c.date]))

(defn new
  [params]
  {:purchase/date     (params :date)
   :purchase/amount   (params :amount)
   :purchase/company  (params :company)
   :purchase/category (params :category)})

;; (defn all [] c.db/purchases)

;; (defn by-company
;;   [company]
;;   (filter #(= company (get % :company)) c.db/purchases))

;; (defn by-month
;;   ([date]
;;    (filter #(c.date/same-month? date (% :date)) c.db/purchases))
;;   ([date purchases]
;;    (filter #(c.date/same-month? date (% :date)) purchases)))

;; (defn by-customer
;;   ([customer-id]
;;    (filter #(= customer-id (% :customer-id)) c.db/purchases))
;;   ([customer-id purchases]
;;    (filter #(= customer-id (% :customer-id)) purchases)))