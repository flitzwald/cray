(ns ray
    (:use vectr))

(defstruct ray :o :d)

(defn make-ray
  [from to]
  (struct ray from (vectr-normalize (vectr-subtract to from))))

(defn ray-point-at
  [ray t]
  (vectr-add (:o ray) (vectr-scale (:d ray) t)))
