(ns challenger-clojure.core
  (:use [clojure pprint])
  (:require [challenger-clojure.models.customer :as customer]
            [challenger-clojure.models.credit-card :as credit-card]
            [challenger-clojure.models.purchase :as purchase]
            [challenger-clojure.db.seed :as seed]
            [challenger-clojure.db.core :as db]))

(defn main []
  (println "Clean database ...")
  (db/delete-database!)

  (def conn (db/open-connection!))

  (println "Create schema ...")
  (db/create-schema! conn)

  (seed/run conn)

  (println "Listing all purchases ")
  (pprint (purchase/all conn))

  (let [most-expensive-purchase (ffirst (purchase/most-expensive conn))
        customer (:purchase/customer most-expensive-purchase)]
    (println "Compra mais cara:")
    (pprint most-expensive-purchase)
    (println "Cliente da compra mais cara:")
    (pprint customer))

  (println "Finish!"))
