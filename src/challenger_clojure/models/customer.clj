(ns challenger-clojure.models.customer
  (:require [datomic.api :as d]
            [challenger-clojure.helpers.uuid-helper :as uuid]))

(defn new
  [params]
  {:customer/id    (uuid/generate (params :id))
   :customer/name  (params :name)
   :customer/cpf   (params :cpf)
   :customer/email (params :email)})

(defn all
  "Returns all clients on database"
  [conn]
  (d/q '[:find (pull ?customer [*])
         :where [?customer :customer/id]] (d/db conn)))

(defn by-cpf
  "Retorna a entidade do cpf passado por parametro"
  [conn customer-cpf]
  (d/q '[:find (pull ?customer [*])
         :in $ ?cpf
         :where [?customer :customer/cpf ?cpf]] (d/db conn) customer-cpf))

(defn without-purchases
  [conn]
  (d/q '[:find (pull ?customer [*])
         :where
         [?customer :customer/id ?customer-id]
         (not-join [?customer]
                   [?purchase :purchase/customer ?customer])] (d/db conn)))

(defn with-most-expensive-purchase
  [conn]
  (d/q '[:find (pull ?customer [*])
         :where [(q '[:find (max ?max-amount)
                      :where [?purchase :purchase/amount ?max-amount]] $)
                 [[?max-amount]]]
         [?purchase :purchase/amount ?amount]
         [(= ?amount ?max-amount)]
         [?purchase :purchase/customer ?customer]] (d/db conn)))