(ns cray
    (:use color image vectr sphere ray))

(if (< (count *command-line-args*) 2 )
  (do
    (println "Usage:\nclj cray.clj <outfile>")
    (System/exit 0) ) )


(let [img    (make-image 400 400)
      eye    (make-vectr 200 200 -500)
      sphere (make-sphere (make-vectr 200 200 300) 100)]
  (image-every-pixel 
   img
   (fn [img x y w h]
       (let [pixel (make-vectr x (- h 1 y) 0 )
             ray (make-ray eye pixel)]
         (image-set-pixel!
          img
          x y
          (if (not (nil? (sphere-intersect sphere ray)))
            blue-color
            black-color)))))

  (image-save img (second *command-line-args* ) ) )
