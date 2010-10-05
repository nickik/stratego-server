(ns stratego.core
  (use [clojure.pprint]))

(defprotocol Ptoken
  (show [this])
  (show-color [this]))

(defrecord token [val color]
  Ptoken
  (show [this] (:val this))
  (show-color [this] (if (= :rot (get this :color)) "R" "B")))



