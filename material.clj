(ns material
    (:use color))

(defstruct material :col :amb :diff :spec :phong)

(defn make-material
  [col amb diff spec phong]
  (struct material col amb diff spec phong) )

(defn material-plastic
  [col]
  (make-material col 0.2 0.8 0.8 64) )
  
(def material-red-plastic (material-plastic red-color))
(def material-green-plastic (material-plastic green-color))
(def material-blue-plastic (material-plastic blue-color))
