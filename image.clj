(ns image
    (:use color))

(defstruct image :img-src)

(defn make-image
  "Constructs an image of w x h pixels"
  [w h]
  (struct image (new java.awt.image.BufferedImage w h
                     (java.awt.image.BufferedImage/TYPE_INT_RGB) ) ) )

(defn image-width
  [img]
  (.getWidth (:img-src img)) )

(defn image-height
  [img] 
  (.getHeight (:img-src img) ) )

(defn image-every-pixel
  "Iterates over every pixel in the image and calls func on it"
  [image func]
  (let [width  (image-width image)
        height (image-height image )]
    (doall (for [y (range height)]
             (doall (for [x (range width)] 
                      (func image x y width height ) ) ) ) ) ) )

(defn image-set-pixel!
  "Sets the pixel-color at position x,y"
  [image x y color]
  (doto (:img-src image) (.setRGB x y (color-to-rgb color ) ) ) )

(defn image-save
  "Saves the image to a given filepath"
  [image file-path]
  (let [file (new java.io.File file-path)]
    (javax.imageio.ImageIO/write (:img-src image) "png" file )) )





