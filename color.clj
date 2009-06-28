(ns color)

(defstruct color :r :g :b)

(defn make-color
  "Constructs a color out of its red, green and blue components"
  [r g b]
  (struct color (float r) (float g) (float b) ) )

(defn color-clamp
  "Makes sure that the components of the color are in legal range"
  [clr]
  (make-color 
   (max (min (:r clr) 1.0) 0.0)
   (max (min (:g clr) 1.0) 0.0)
   (max (min (:b clr) 1.0) 0.0 )))


(defn color-add
  "Adds some colors"
  [& args]
  (color-clamp
   (make-color 
    (reduce (fn [v obj] (+ (:r obj) v ) ) 0 args) 
    (reduce (fn [v obj] (+ (:g obj) v ) ) 0 args) 
    (reduce (fn [v obj] (+ (:b obj) v ) ) 0 args) ) )
  )

(defn color-scale
  [clr in-coeff]
  (let [coeff (float in-coeff)]
    (make-color (* coeff (:r clr))
                (* coeff (:g clr))
                (* coeff (:b clr)) ) ) )

(defn color-to-rgb
  "Converts the color into a 32-Bit integer compatible with
   java.awt.image.BufferedImage.TYPE_INT_RGB"
  [clr] 
  (+ (bit-shift-left (int (* (:r clr) 255) ) 16 ) 
     (bit-shift-left (int (* (:g clr) 255) ) 8 )
     (int (* (:b clr) 255) ) ) )

; Some predefined colors
(def black-color (make-color 0 0 0))
(def white-color (make-color 1 1 1))
(def red-color   (make-color 1 0 0))
(def green-color (make-color 0 1 0))
(def blue-color  (make-color 0 0 1))
