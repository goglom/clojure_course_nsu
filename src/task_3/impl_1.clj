(ns task-3.impl-1)


(defn fn1 [x] x)
(defn fn1-int [x] (/ (* x x) 2))


; (f(0) + 2*f(h) + 2*f(2h) + ... + 2*f(n*h)) + f(h * (n+1))) / 2 * h
(defn trapeze [f step ind]
  (let [f_a (f (* step ind))
        f_b (f (* step (inc ind)))]
    (-> (+ f_a f_b) (/ 2) (* step))))

(def trapeze-mem (memoize trapeze))

(def int-sum
  (memoize
    (fn [int-base f step ind]
      (reduce
        #(+ %1 (int-base f step %2))
        0
        (range 0 ind)))))

(defn integrate [int-base f step]
  (fn [t]
    (let [ind (int (/ t step))
          trapeze-sum (int-sum int-base f step ind)
          f-ind (f (* ind step))
          local-step (- t (* ind step))]
      (-> (f t) (+ f-ind) (/ 2) (* local-step) (+ trapeze-sum)))))

(def t 10.05)
(def STEP 0.0001)

(def int-f (integrate trapeze fn1 STEP))
(def int-f-mem (integrate trapeze-mem fn1 STEP))
(def int-f-mem-2 (integrate trapeze-mem (memoize fn1) STEP))


(def X (range 0 30 0.33))

(defn test-foo [f]
  (println (take 3 (time (vec (map f X))))))

(test-foo int-f)
(test-foo int-f-mem)
(test-foo int-f-mem-2)
