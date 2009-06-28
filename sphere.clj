(ns sphere
    (:use vectr))

(defstruct sphere :c :r)

(defn make-sphere
  "Creates a sphere out of its center and radius"
  [center radius] 
  (struct sphere center (float radius)))

(defn sphere-intersect
  "Tests whether a ray intersects a sphere.
   Returns the intersection points or nil"
  [sphere ray]
  (let [v (vectr-subtract (:o ray) (:c sphere))
        v-dot-d (vectr-dot v (:d ray))
        q (- (vectr-dot v v) 
             (Math/pow (:r sphere) 2.0))
        sub-sqrt (- (* v-dot-d v-dot-d) q)]
    (if (> sub-sqrt 0.0) 
      (let [sqrt (Math/sqrt sub-sqrt)] 
        [(- (* v-dot-d -1) sqrt)
         (+ (* v-dot-d -1) sqrt)])
      nil )))