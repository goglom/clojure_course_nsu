(ns task_2.test_1
  (:require [clojure.test :refer :all]
            [task_2.impl_1 :refer :all]))


(let [n 40
      res (primes-sequence n)]
  (println n "number: " (nth res (- n 1)))
  (println "Calculated numbers count: " (count res)))


(deftest task_2_1-test
  (testing "Testing of nth-prime function"
    (is (= 2 (prime-nth 1)))
    (is (= 3 (prime-nth 2)))
    (is (= 5 (prime-nth 3)))
    (is (= 7 (prime-nth 4)))
    (is (= 11 (prime-nth 5)))
    (is (= 13 (prime-nth 6)))
    (is (= 17 (prime-nth 7)))
    (is (= 541 (prime-nth 100)))
    (is (= 4421 (prime-nth 601)))
    (is (= 7919 (prime-nth 1000)))
    ))

(run-test task_2_1-test)
