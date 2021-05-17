(ns challenger-clojure.models.customer
  (:require [challenger-clojure.db :as c.db]))

(defn all [] c.db/customers)

(defn by-id
  [customer-id]
  (filter #(= customer-id (% :id)) c.db/customers))