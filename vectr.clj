(ns vectr)

(defstruct vectr :x :y :z)

(defn make-vectr
  "Creates a new vector out of its  x, y and z coordinate"
  [x y z]
  (struct vectr (float x) (float y) (float z)) )

(defn vectr-add
  "Adds two vectors"
  [a b]
  (make-vectr (+ (:x a) (:x b))
        (+ (:y a) (:y b))
        (+ (:z a) (:z b))))

(defn vectr-subtract
  "Subtracts the second vector off the first"
  [a b]
  (make-vectr (- (:x a) (:x b))
        (- (:y a) (:y b)) 
        (- (:z a) (:z b))))

(defn vectr-dot
  "Computes the dot product of two vectors"
  [a b]
  (+ (* (:x a) (:x b))
     (* (:y a) (:y b))
     (* (:z a) (:z b))))

(defn vectr-scale
  "Scales the a vector"
  [vectr scalar]
  (make-vectr (* scalar (:x vectr))
        (* scalar (:y vectr))
        (* scalar (:z vectr))))

(defn vectr-length-square
  "Computes the square of the vectors length"
  [vectr]
  (+ (* (:x vectr) (:x vectr))
     (* (:y vectr) (:y vectr))
     (* (:z vectr) (:z vectr))))

(defn vectr-length
  "Computes the vectors length"
  [vectr]
  (Math/sqrt (vectr-length-square vectr)))

(defn vectr-normalize
  "Scales the vector, so that its length is 1"
  [vectr]
  (vectr-scale vectr (/ 1 (vectr-length vectr))))

(defn vectr-cross
  "Computes the cross product of two vectors"
  [a b]
  (make-vectr (- (* (:y a) (:z b))
           (* (:z a) (:y b)))
        (- (* (:z a) (:x b))
           (* (:x a) (:z b)))
        (- (* (:x a) (:y b))
           (* (:y a) (:x b)))))
