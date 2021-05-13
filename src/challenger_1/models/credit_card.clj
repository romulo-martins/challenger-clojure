(ns challenger-1.models.credit-card
  (:require [challenger-1.db :as c.db]))

(defn all [] c.db/credit-cards)

(defn by-customer-id
  [customer-id]
  (filter #(= customer-id (% :customer-id)) c.db/credit-cards))