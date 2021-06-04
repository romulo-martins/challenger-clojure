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

