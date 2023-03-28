(ns task-2.impl_2)

(defn has-dividers [num coll]
  (some #(= (mod num %) 0) coll))


(defn filter-primes-in-range
  ([primes numbers]
   (let [last-num (last numbers)
         ; Use primes less than sqrt(N), where N the last element from new range
         sub-primes (take-while #(<= (* % %) last-num) primes)]
     (filter #(not (has-dividers % sub-primes)) numbers))))


; Must fill first prime numbers no less than sqrt(STEP)
; For STEP = 100, its equals 10.. (2, 3, 5, 7, 11)[12, 13, 14, 15 ...20]&[21]
;                                                  b              b + D
; sqrt(b + D) < b
; b + D < b^2 ==> D = b^2 - b - 1
; For STEP = 1000: ~31.622.. (2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37)
(let [START-SEQ '(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37)
      MAX (last START-SEQ)
      STEP (- (* MAX (- MAX 1)) 1)]
  (defn primes
    ([] (lazy-cat START-SEQ (primes (+ 1 MAX))))
    ([lower-b] (let [upper-b (+ lower-b STEP)]
                 (lazy-cat
                   (filter-primes-in-range (primes) (range lower-b upper-b))
                   (primes upper-b))))))


;(println (take 100 (primes)))
(println (time (nth (primes) 1000000)))