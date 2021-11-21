(ns clodeler.core
  (:gen-class)
  (:require [scad-clj.scad :as scad])
  (:use [scad-clj.model]))

(def cube-diameter 40)
(def screw-hole-diameter 6.2)
(def steckschluessel-diameter 16)

(def negative
  (union
    (->> (cylinder (/ screw-hole-diameter 2) 50)
         (with-center false)
         (with-fn 120))
    (->> (cylinder (/ steckschluessel-diameter 2) 40)
         (with-center false)
         (with-fn 120)
         (translate [0 0 10]))))

(def positive
  (union
    (->> (import "parts/dp_attachment.stl")
         (rotate [0 (deg->rad 180) 0])
         (translate [15 -1 35]))
    (->> (cube cube-diameter cube-diameter cube-diameter)
         (translate [0 0 20]))))

(def model
  (difference positive negative))

(defn write-scad
  [model]
  (spit "model.scad" (scad/write-scad model)))

(defn -main
  "write main model to scad file"
  [& args]
  (write-scad model))
