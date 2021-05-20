(ns challenger-clojure.models.purchase
  (:require [challenger-clojure.helpers.date-helper :as date]))

(defn new
  [params]
  {:purchase/date     (params :date)
   :purchase/amount   (params :amount)
   :purchase/company  (params :company)
   :purchase/category (params :category)})
