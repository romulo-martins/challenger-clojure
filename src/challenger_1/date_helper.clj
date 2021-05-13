(ns challenger-1.date-helper
  (:require [clojure.string :as str]))

(defn get-month
  [date]
  (->
   (str/split date #"-")
   (get 1)))

(defn get-year
  [date]
  (->
   (str/split date #"-")
   (get 0)))

(defn same-month?
  [date-a date-b]
  (and
   (= (get-month date-a) (get-month date-b))
   (= (get-year date-a) (get-year date-b))))