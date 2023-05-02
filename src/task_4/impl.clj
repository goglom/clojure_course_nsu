(ns task-4.impl)

;(defn const [value])

(defn variable [name]
  {:pre [(keyword? name)]}
  (list ::var name))

(defn and-op [arg1 arg2 & rest]
  (->> rest (cons arg2) (cons arg1) (cons ::and)))

(variable :sa)

(and-op 1 2 3 4)