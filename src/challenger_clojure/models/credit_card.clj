(ns challenger-clojure.models.credit-card
  (:require [challenger-clojure.db :as c.db]))

(defn new
  [params]
  {:customer/number      (params :number)
   :customer/cvv         (params :cvv)
   :customer/valid-date  (params :valid-date)
   :customer/limit       (params :limit)
   :customer/customer-id (params :customer-id)})

;; (defn all [] c.db/credit-cards)

;; (defn by-customer-id
;;   [customer-id]
;;   (filter #(= customer-id (% :customer-id)) c.db/credit-cards))