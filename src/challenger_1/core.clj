(ns challenger-1.core
  (:require [challenger-1.models.purchase :as c.purchase]
            [challenger-1.logic :as c.logic]
            [clojure.pprint :as pp]))

(defn main
  []
  (println "\n Listagem de compras realizadas:")
  (pp/pprint (c.purchase/all))

  (println "\n Total dos gastos agrupados por categoria:")
  (pp/pprint (c.logic/total-amount-by-category (c.purchase/all)))

  (println "\n Busca de compras por estabelecimento: (Ex: Ri Happy)")
  (pp/pprint (c.purchase/by-company "Ri Happy"))

  (println "\n Calculo da fatura do mes: (Ex: Cliente 1, Mes 03/2021)")
  (pp/pprint (c.logic/customer-invoice-by-month 1 "2021-03")))