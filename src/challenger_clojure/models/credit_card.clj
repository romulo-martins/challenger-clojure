(ns challenger-clojure.models.credit-card
  (:require [challenger-clojure.db :as c.db]))

(defn all [] c.db/credit-cards)

(defn by-customer-id
  [customer-id]
  (filter #(= customer-id (% :customer-id)) c.db/credit-cards))