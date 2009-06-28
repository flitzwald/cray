(ns light)

(defstruct light :pos :col)

(defn make-light
  "Creates a light source out of its position and color"
  [pos col]
  (struct light pos col))