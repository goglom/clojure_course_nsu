(ns task-3.compare
  (:require [clojure.test :refer :all])
  (:use [task-3.impl-1 :only (memtegrate)]
        [task-3.impl-2 :only (integrate)]))

;(defn test-f [x] (pow x 10))
(defn test-f [x] (do (Thread/sleep 2) x))
(defn f-int [x] (/ (* x x) 2))


(def STEP 0.01)
(def test-range (range 0 5 0.19))


(defn test-func [f & r]
  (let [X (shuffle test-range)]
    (when r (apply println r))
    (println (take 3 (time (doall (map f X)))))
    (println)))


(def mem-int (memtegrate test-f STEP))
(def seq-int (integrate test-f STEP))

(* (nth (iterate inc 0) 1000000) 2)

(test-func mem-int)
(test-func mem-int)
(test-func mem-int)
(test-func seq-int)
(test-func seq-int)
(test-func seq-int)
