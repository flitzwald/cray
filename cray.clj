(ns cray
    (:use color image vectr sphere ray light material))

(if (< (count *command-line-args*) 2 )
  (do
    (println "Usage:\nclj cray.clj <outfile>")
    (System/exit 0) ) )

(defn compute-reflection-ray
  [ray point normal]
  (make-ray point
            (vectr-subtract 
             (:d ray) 
             (vectr-scale normal (* (vectr-dot (:d ray) 
                                               normal) 2)))))

(defn phong-compose
  [point ray normal material light] 
  (let [reflection  (compute-reflection-ray ray point normal)
        light-vectr (vectr-normalize (vectr-subtract point 
                                                     (:pos light)))
        half-vectr  (vectr-normalize (vectr-add light-vectr (:d ray)))
        diffuse     (max 0.0 (vectr-dot light-vectr 
                                        (vectr-scale normal -1) ) )
        specular    (Math/pow (max 0.0 
                                   (vectr-dot half-vectr 
                                              (vectr-scale normal -1)))
                              (:phong material))
        color (:col material)]
    (color-add (color-scale color (:amb material) )
               (color-scale color (* diffuse (:diff material)))
               (color-scale (:col light) (* specular 
                                            (:spec material))))))

(defn compute-color
  [sphere light ray]
  (let [intersects (sphere-intersect sphere ray)
        material (:material sphere)]
    (if (nil? intersects) 
      black-color
      (let [point (ray-point-at ray (first intersects))
            normal (sphere-surface-normal sphere point)
            reflection (compute-reflection-ray ray point normal)
            light-vectr (vectr-normalize (vectr-subtract point 
                                                         (:pos light)))
            diff (max 0.0 (vectr-dot light-vectr 
                                     (vectr-scale normal -1) ) )
            half-vectr (vectr-normalize (vectr-add light-vectr (:d ray)))
            spec (Math/pow (max 0.0 
                                (vectr-dot half-vectr 
                                           (vectr-scale normal -1)))
                           (:phong material))]
        (phong-compose point ray normal material light)))))

(let [img    (make-image 400 400)
      eye    (make-vectr 200 200 -500)
      sphere (make-sphere (make-vectr 200 200 300) 
                          100 
                          material-blue-plastic)
      light  (make-light (make-vectr 0 800 0) white-color) ]
    (image-every-pixel 
     img
     (fn [img x y w h]
         (let [pixel (make-vectr x (- h 1 y) 0 )
               ray (make-ray eye pixel)]
           (image-set-pixel!
            img
            x y
            (compute-color sphere light ray)))))
  
  (image-save img (second *command-line-args* )))
