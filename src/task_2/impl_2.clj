(ns task-2.impl-2)

(defn has-dividers [num coll]
  (some #(= (mod num %) 0) coll))


(defn filter-primes-in-range
  ([primes numbers]
   (let [max-prime (last numbers)
         sub-primes (take-while #(<= (* % %) max-prime) primes)]
     (filter #(not (has-dividers % sub-primes)) numbers))))


; Must fill first prime numbers no less than (1 + sqrt(1 + 4 * STEP)) / 2
; For STEP = 100, its equals ~10.512.. (2, 3, 5, 7, 11)
; For STEP = 1000: ~32.126.. (2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37)
(let [START-SEQ '(2, 3, 5, 7, 11)
      STEP 100]
  (defn primes
    ([] (lazy-cat START-SEQ (primes (+ 1 (last START-SEQ)))))
    ([lower-b] (let [upper-b (+ lower-b STEP)]
                 (lazy-cat
                   (filter-primes-in-range (primes) (range lower-b upper-b))
                   (primes upper-b))))))


(nth (primes) 100000)
