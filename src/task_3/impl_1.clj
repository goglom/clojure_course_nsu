(ns task-3.impl-1)

(defn fn1 [x] (do (Thread/sleep 1) x))
(defn fn1-int [x] (/ (* x x) 2))

(defn trapeze [f step ind]
  (let [f_a (f (* step ind))
        f_b (f (* step (inc ind)))]
    (-> (+ f_a f_b) (/ 2) (* step))))

(def trapeze-mem (memoize trapeze))

(defn int-sum [int-base f step]
  (fn [ind]
    (reduce
      #(+ %1 (int-base f step %2))
      0
      (range 0 ind))))

(defn int-sum-mem [int-base f step]
   (memoize (int-sum int-base f step)))

(defn integrate [sum-base f step]
  (fn [t]
    (let [ind (int (/ t step))
          trapeze-sum (sum-base ind)
          f-ind (f (* ind step))
          local-step (- t (* ind step))]
      (-> (f t) (+ f-ind) (/ 2) (* local-step) (+ trapeze-sum)))))


;(defn fn1-mem (memoize fn1))

(def STEP 0.01)

(def int-f       (integrate (int-sum     trapeze     fn1 STEP) fn1 STEP))
(def int-f-mem-1 (integrate (int-sum     trapeze-mem fn1 STEP) fn1 STEP))
(def int-f-mem-2 (integrate (int-sum-mem trapeze     fn1 STEP) fn1 STEP))
(def int-f-mem-3 (integrate (int-sum-mem trapeze-mem fn1 STEP) fn1 STEP))
;(def int-f-mem-4 (integrate (int-sum-mem trapeze-mem fn1-mem STEP) fn1-mem STEP))

(def X (range 0 10 1.31))

(defn test-foo [f]
  (println (take 3 (time (doall (map f X))))))

;(test-foo int-f)
;(test-foo int-f-mem-1)
;(test-foo int-f-mem-2)
(test-foo int-f-mem-3)
;(test-foo int-f-mem-4)

(defn memtegrate [fn step]
  (integrate (int-sum-mem trapeze-mem fn step) fn step))

