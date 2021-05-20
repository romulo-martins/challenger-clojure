(ns challenger-clojure.db.seed
  (:use [clojure pprint])
  (:require [challenger-clojure.db.core :as db]
            [challenger-clojure.models.customer :as customer]
            [challenger-clojure.models.credit-card :as credit-card]
            [challenger-clojure.models.purchase :as purchase]))

(def customers
  [{:id "a621fad5-d1c4-410f-a7d7-79b8a5c0875c" :name "Júlia Rameira"     :cpf "642441182" :email "julia_rameira@gmail.com"}
   {:id "8151e594-bac3-41f7-aacb-c9bc068c5b68" :name "Arthur Henrique"   :cpf "999969705" :email "arthur_henri@outlook.com"}
   {:id "45b0769d-cf93-458c-a63b-1092191fdc4c" :name "Amanda Assumpção"  :cpf "240568126" :email "amanda_assump@@hotmail.com"}
   {:id "acba1f0c-de44-4ca2-9379-dde23e709b64" :name "Leonardo Nogueira" :cpf "336987973" :email "leo_nog@hotmail.com"}
   {:id "5fbb381e-6fde-403a-8071-389d818d4de8" :name "Gabriel Batista"   :cpf "814957331" :email "gab_batista@gmail.com"}])

(def credit-cards
  [{:number "3528-3070-0184-0293" :cvv "418" :valid-date "2022-06-25" :limit 2000.00 :customer-id "a621fad5-d1c4-410f-a7d7-79b8a5c0875c"}
   {:number "6574-2810-2736-4628" :cvv "580" :valid-date "2025-12-12" :limit 4000.00 :customer-id "8151e594-bac3-41f7-aacb-c9bc068c5b68"}
   {:number "5869-6294-3469-8511" :cvv "112" :valid-date "2023-09-26" :limit 900.00  :customer-id "45b0769d-cf93-458c-a63b-1092191fdc4c"}
   {:number "6467-6824-2334-9677" :cvv "350" :valid-date "2024-10-08" :limit 2000.00 :customer-id "acba1f0c-de44-4ca2-9379-dde23e709b64"}
   {:number "5694-5858-6567-7730" :cvv "582" :valid-date "2024-10-24" :limit 5000.00 :customer-id "5fbb381e-6fde-403a-8071-389d818d4de8"}])

(def purchases
  [{:date "2020-04-21" :amount 320.90  :company "Lojas Americanas" :category "Home"        :customer-id "a621fad5-d1c4-410f-a7d7-79b8a5c0875c"}
   {:date "2018-01-17" :amount 700.26  :company "Ri Happy"         :category "Toys"        :customer-id "a621fad5-d1c4-410f-a7d7-79b8a5c0875c"}
   {:date "2021-03-20" :amount 781.12  :company "Leroy Merlin"     :category "Home"        :customer-id "a621fad5-d1c4-410f-a7d7-79b8a5c0875c"}
   {:date "2021-03-14" :amount 3996.1  :company "Ri Happy"         :category "Toys"        :customer-id "a621fad5-d1c4-410f-a7d7-79b8a5c0875c"}
   {:date "2018-08-18" :amount 117.37  :company "Dominos"          :category "Food"        :customer-id "8151e594-bac3-41f7-aacb-c9bc068c5b68"}
   {:date "2020-01-20" :amount 2300.90 :company "Casas Bahia"      :category "Electronics" :customer-id "8151e594-bac3-41f7-aacb-c9bc068c5b68"}
   {:date "2016-07-03" :amount 747.73  :company "Natura"           :category "Beauty"      :customer-id "45b0769d-cf93-458c-a63b-1092191fdc4c"}
   {:date "2019-11-12" :amount 323.2   :company "Amazon"           :category "Books"       :customer-id "acba1f0c-de44-4ca2-9379-dde23e709b64"}
   {:date "2016-08-02" :amount 752.99  :company "Ponto Frio"       :category "Electronics" :customer-id "acba1f0c-de44-4ca2-9379-dde23e709b64"}
   {:date "2020-08-11" :amount 48.41   :company "Burguer King"     :category "Food"        :customer-id "5fbb381e-6fde-403a-8071-389d818d4de8"}
   {:date "2017-09-06" :amount 67.4    :company "McDonalds"        :category "Food"        :customer-id "5fbb381e-6fde-403a-8071-389d818d4de8"}
   {:date "2020-04-20" :amount 1297.58 :company "Leroy Merlin"     :category "Home"        :customer-id "5fbb381e-6fde-403a-8071-389d818d4de8"}])

(defn seed-customers! [conn]
  (let [customers-data (map customer/new customers)]
    (db/insert-data! conn customers-data)))

(defn seed-credit-cards! [conn]
  (let [credit-cards-data (map credit-card/new credit-cards)]
    (db/insert-data! conn credit-cards-data)))

(defn seed-purchases! [conn]
  (let [purchase-data (map purchase/new purchases)]
    (db/insert-data! conn purchase-data)))

(defn run
  [conn]
  (println "Seeding database with customers ...")
  (seed-customers! conn)
  (println "Seeding database with credit cards ...")
  (seed-credit-cards! conn)
  (println "Seeding database with purchases ...")
  (seed-purchases! conn)
  (println "Successful seeding!"))
