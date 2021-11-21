(ns clodeler.core
  (:gen-class)
  (:require [scad-clj.scad :as scad])
  (:use [scad-clj.model]))

(def model
  (cube 10 10 10))

(defn write-scad
  [model]
  (spit "model.scad" (scad/write-scad model)))

(defn -main
  []
  (write-scad model))
