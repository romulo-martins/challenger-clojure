(ns challenger-1.models.customer
  (:require [challenger-1.db :as c.db]))

(defn all [] c.db/customers)

(defn by-id
  [customer-id]
  (filter #(= customer-id (% :id)) c.db/customers))