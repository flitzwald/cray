(ns cray
    (:use color image))

(if (< (count *command-line-args*) 2 )
  (do
    (println "Usage:\nclj cray.clj <outfile>")
    (System/exit 0) ) )

(let [img (make-image 400 400)]
  (image-every-pixel 
   img
   (fn [img x y w h]
       (let [dx (- x (/ (image-width img) 2)) 
             dy (- y (/ (image-height img) 2))]
         (image-set-pixel!
          img
          x y
          (if (< (+ (* dx dx) (* dy dy) ) (* 150 150) )
            blue-color
            black-color ) ) ) ) )

  (image-save img (second *command-line-args* ) ) )
