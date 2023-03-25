(ns task_2.impl_1
  (:use [clojure.math]))


(defn mark-nonprime [coll i n]
  (reduce
    (fn [l r] (assoc l r false))
    coll
    (range (* i i) n i)))


(defn get-primes-bit-array [n]
  (reduce
    (fn [l r] (mark-nonprime l r n))
    (vec (repeat (+ n 1) true))
    (take-while
      (fn [i] (<= (* i i) n))
      (iterate inc 2))))


(defn prime-nth-upper-bound
  "Return upper bound of n-th prime number"
  [n]
  (cond
    (= n 1) 2
    (= n 2) 3
    (= n 3) 5
    (= n 4) 7
    (= n 5) 11
    ; Based on estimating: p_n < n * (log(n) + log(log(n)))
    (>= n 6) (-> n
                 (log)
                 (+ (-> n (log) (log)))
                 (* n)
                 (round))
    :else (assert false "Expected num greater than 0")))


(defn primes-sequence [n]
  (let
    [upper-bound (prime-nth-upper-bound n)
     arr (get-primes-bit-array upper-bound)]
    (reduce
      (fn [l r] (if (nth arr r)
                  (conj l r)
                  l))
      []
      (range 2 (+ upper-bound 1)))))


(defn prime-nth [n]
  (nth (primes-sequence n) (- n 1)))
