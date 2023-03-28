(ns task-3.impl-2)

(defn trapeze [f step ind]
  (let [f_a (f (* step ind))
        f_b (f (* step (inc ind)))]
    (-> (+ f_a f_b) (/ 2) (* step))))


(defn partial-sum-seq [f step]
  (let [tr (partial trapeze f step)]
    (iterate
      (fn [[val ind]]
        (list (+ val (tr ind)) (inc ind)))
      (list 0 0))))


;(defn fn1 [x] (do (Thread/sleep 1) x))
(defn fn1 [x] (* x x))
(defn int-fn1 [x] (-> (* x x) (* x) (/ 3)))

(def STEP 0.0000001)

(def fn1-sum-seq (partial-sum-seq fn1 STEP))

(println (take 10 fn1-sum-seq))


(defn integrate [f step]
  (let [f-sum-seq (partial-sum-seq f step)]
    (fn [t]
      (let [ind (int (/ t step))
            trapeze-sum (first (nth f-sum-seq ind))
            f-ind (f (* ind step))
            local-step (- t (* ind step))]
        (-> (f t) (+ f-ind) (/ 2) (* local-step) (+ trapeze-sum))))))


(def exp-int-fn1 (integrate fn1 STEP))
(def x 100.0)
(println "Expected: " (int-fn1 x))
(println "Numeric : " (exp-int-fn1 x))