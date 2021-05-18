(ns challenger-clojure.models.customer
  (:require [datomic.api :as d]))

(defn new
  [params]
  {:customer/name  (params :name)
   :customer/cpf   (params :cpf)
   :customer/email (params :email)})

(defn by-cpf
  "Retorna a entidade do cpf passado por parametro"
  [conn customer-cpf]
  (d/q '[:find (pull ?entity [*])
         :in $ ?cpf
         :where [?entity :customer/cpf ?cpf]] (d/db conn) customer-cpf))

(defn all
  "Retorna todas as entidades que estão no datomic"
  [conn]
  (d/q '[:find (pull ?entity [*])
         :where [?entity :customer/cpf]] (d/db conn)))