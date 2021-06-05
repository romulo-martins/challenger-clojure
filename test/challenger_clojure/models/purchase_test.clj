(ns challenger-clojure.models.purchase-test
  (:require [clojure.test :refer :all]
            [challenger-clojure.models.purchase :refer :all]))

(deftest add-test
  (testing "Test adding in a new purchase in a empty purchases list"
    (let [purchases    []
          new-purchase {:date "2018-01-17" :amount 700.26 :company "Ri Happy" :category "Toys" :customer-id 1}]
      (is (= (add purchases new-purchase)
             [new-purchase]))))

  (testing "Test adding a new purchase with another purchases"
    (let [old-purchase {:date "2018-01-17" :amount 700.26 :company "Ri Happy" :category "Toys" :customer-id 1}
          purchases    [old-purchase]
          new-purchase {:date "2020-08-11" :amount 48.41 :company "Burguer King" :category "Food" :customer-id 5}]
      (is (= (add purchases new-purchase)
             [old-purchase, new-purchase]))
      (is (not= (add purchases new-purchase)
                purchases)))))

(deftest total-amount-by-category-test
  (testing "when we given a empty purchases array should return empty response"
    (let [purchases []]
      (is (= (total-amount-by-category purchases) []))))

  (testing "when we give purchases without category should return empty response"
    (let [purchases [{:date "2018-01-17" :amount 700.26 :company "Ri Happy" :customer-id 1}
                     {:date "2020-08-11" :amount 48.41 :company "Burguer King" :customer-id 5}]]
      (is (= (total-amount-by-category purchases) []))))

  (testing "when we given a single purchase should return only his amount as total amount"
    (let [purchases [{:date "2018-01-17" :amount 700.26 :company "Ri Happy" :category "Toys" :customer-id 1}]]
      (is (= (total-amount-by-category purchases)
             [{:category "Toys" :total-amount 700.26}]))))

  (testing "when we give purchases with and without category should return only thats have category"
    (let [purchases [{:date "2018-01-17" :amount 700.26 :company "Ri Happy" :category "Toys" :customer-id 1}
                     {:date "2020-08-11" :amount 48.41 :company "Burguer King" :customer-id 5}]]
      (is (= (total-amount-by-category purchases)
             [{:category "Toys" :total-amount 700.26}]))))

  (testing "when we give purchases with many categories should the summary of each category"
    (let [purchases [{:date "2020-04-21" :amount 320.90 :company "Lojas Americanas" :category "Home" :customer-id 1}
                     {:date "2018-01-17" :amount 700.26 :company "Ri Happy" :category "Toys" :customer-id 1}
                     {:date "2021-03-20" :amount 781.12 :company "Leroy Merlin" :category "Home" :customer-id 1}
                     {:date "2021-03-14" :amount 3996.1 :company "Ri Happy" :category "Toys" :customer-id 1}
                     {:date "2018-08-18" :amount 117.37 :company "Dominos" :category "Food" :customer-id 2}
                     {:date "2020-01-20" :amount 2300.90 :company "Casas Bahia" :category "Electronics" :customer-id 2}]]
      (is (= (total-amount-by-category purchases)
             [{:category "Home" :total-amount 1102.02}
              {:category "Toys" :total-amount 4696.36}
              {:category "Food" :total-amount 117.37}
              {:category "Electronics" :total-amount 2300.90}])))))