(ns task_2.test_2
  (:require [clojure.test :refer :all]
            [task-2.impl_2 :refer :all]))

(deftest task_2_1-test
  (testing "Testing of nth-prime function"
    (is (= 2 (nth (primes) 0)))
    (is (= 3 (nth (primes) 1)))
    (is (= 5 (nth (primes) 2)))
    (is (= 7 (nth (primes) 3)))
    (is (= 11 (nth (primes) 4)))
    (is (= 13 (nth (primes) 5)))
    (is (= 17 (nth (primes) 6)))
    (is (= 541 (nth (primes) 99)))
    (is (= 4421 (nth (primes) 600)))
    (is (= 7919 (nth (primes) 999)))
    ))

(run-test task_2_1-test)
