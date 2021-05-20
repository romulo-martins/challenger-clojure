(ns challenger-clojure.core
  (:use [clojure pprint])
  (:require [challenger-clojure.models.customer :as customer]
            [challenger-clojure.models.credit-card :as credit-card]
            [challenger-clojure.db.seed :as seed]
            [challenger-clojure.db.core :as db]))

(defn main []  
  (println "Clean database ...")
  (db/delete-database!)

  (def conn (db/open-connection!))

  (println "Create schema ...")
  (db/create-schema! conn)

  (seed/run conn)

  (println "All customers")
  (pprint (customer/all conn))

  (println "All credit cards")
  (pprint (credit-card/all conn))

  (println "Finding customer from cpf (336987973) ...")
  (pprint (customer/by-cpf conn "336987973")))
