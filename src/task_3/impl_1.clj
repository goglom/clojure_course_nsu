(ns task-3.impl-1)

; (f(0) + 2*f(h) + 2*f(2h) + ... + 2*f(n*h)) + f(h * (n+1))) / 2 * h
(defn trapeze [f step ind]
  (let [f_0 (f 0)
        f_lst (f (* (+ ind 1) step))
        internal-sum (reduce #(+ %1 (f (* %2 step))) 0 (range 1 ind))]
    (-> (+ f_0 f_lst) (/ 2) (+ internal-sum) (* step))
    ))

(defn time-print [f]
  (println (time f)))

(defn fn1 [x] x)
(defn fn1-int [x] (/ (* x x) 2))
(println (trapeze fn1 0.1 100))

(def trapeze-mem (memoize trapeze))

(defn integrate [int-base f step]
  (fn [t]
    (let [ind (int (/ t step))
          trapeze-sum (int-base f step ind)
          f-ind (f (* ind step))
          local-step (- t (* ind step))]
      (-> (f t) (+ f-ind) (/ 2) (* local-step) (+ trapeze-sum)))))

(def t 10.05)
(println "Expected: " (fn1-int t))
(time-print ((integrate trapeze fn1 0.00001) t))
(time-print ((integrate trapeze-mem fn1 0.00001) t))