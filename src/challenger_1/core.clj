(ns challenger-1.core
  (:require [challenger-1.db :as c.db]
            [challenger-1.helpers.search-helper :as c.searcher]
            [challenger-1.logic :as c.logic]
            [clojure.pprint :as pp]))

(defn main
  []
  (println "\n Listagem de compras realizadas:")
  (pp/pprint c.db/purchases)

  (println "\n Total dos gastos agrupados por categoria:")
  (pp/pprint (c.logic/total-amount-by-category c.db/purchases))

  (println "\n Busca de compras por estabelecimento: (Ex: Ri Happy)")
  (pp/pprint (c.searcher/purchases-by-company "Ri Happy" c.db/purchases))

  (println "\n Calculo da fatura do mes: (Ex: Cliente 1, Mes 03/2021)")
  (pp/pprint (c.logic/customer-invoice-by-month 1 "2021-03" c.db/purchases)))