(ns challenger-clojure.db.schema)

(def instance
  [; Customer  
   {:db/ident       :customer/id
    :db/valueType   :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity
    :db/doc         "Customers uuid identifier"}
   {:db/ident       :customer/name
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
    :db/doc         "Customers email"}

  ; Credit Card
   {:db/ident       :credit-card/id
    :db/valueType   :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity
    :db/doc         "Credit card uuid identifier"}
   {:db/ident       :credit-card/number
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Credit card number"}
    {:db/ident       :credit-card/cvv
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Credit card cvv"}
   {:db/ident       :credit-card/valid-date
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Credit card valid date"}
   {:db/ident       :credit-card/limit
    :db/valueType   :db.type/double
    :db/cardinality :db.cardinality/one
    :db/doc         "Credit card limit"}
   {:db/ident       :credit-card/customer
    :db/valueType   :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/doc         "Customer reference to credit card"}])