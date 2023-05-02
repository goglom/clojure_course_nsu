(ns task-3.impl-2)

(defn partial-sum-seq [f step]
  (iterate
    (fn [[prev-sum ind f_a]]
      (let [next-ind (inc ind)
            f_c (f (* step ind))
            f_b (f (* step next-ind))]
        (list                                               ; f_a ~ f_C
          (+ prev-sum (-> (+ f_c f_b) (/ 2) (* step)))
          next-ind
          f_b)))
    (list 0 0 (f 0))))


;(defn fn1 [x] (do (Thread/sleep 1) x))
(defn fn1 [x] (* x x))
(defn int-fn1 [x] (-> (* x x) (* x) (/ 3)))

(def STEP 0.00001)

;(def fn1-sum-seq (partial-sum-seq fn1 STEP))
;(println (take 10 fn1-sum-seq))


(defn integrate [f step]
  (let [f-sum-seq (partial-sum-seq f step)]
    (fn [t]
      (let [ind (int (/ t step))
            cur-block (nth f-sum-seq ind)
            trapeze-sum (first cur-block)
            f-ind (nth cur-block 2)
            local-step (- t (* ind step))]
        (-> (f t) (+ f-ind) (/ 2) (* local-step) (+ trapeze-sum))))))


(def exp-int-fn1 (integrate fn1 STEP))
(def x 100.0)
;(println "Expected: " (int-fn1 x))
;(println "Numeric : " (exp-int-fn1 x))