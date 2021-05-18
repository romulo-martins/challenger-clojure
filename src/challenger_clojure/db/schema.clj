(ns challenger-clojure.db.schema)

(def customer
    [{:db/ident       :customer/name
      :db/valueType   :db.type/string
      :db/cardinality :db.cardinality/one
      :db/doc         "Customers name"}
     {:db/ident       :customer/cpf
      :db/valueType   :db.type/string
      :db/cardinality :db.cardinality/one
      :db/doc         "Customers brazilian identifier called CPF."},
     {:db/ident       :customer/email
      :db/valueType   :db.type/string
      :db/cardinality :db.cardinality/one
      :db/doc         "Customers email"}])