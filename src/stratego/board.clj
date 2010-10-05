(ns stratego.board
  (use [clojure.pprint]))

(def board-side 10)

(def water-ver [3 4 7 8])
(def water-hor [5 6])
(def water (vec (for [x water-ver y water-hor] [x y])))

(defrecord field [ground x y token])

(defn cfield [ground x y token]
  (field. ground x y token))

(defn init-board [x y]
  (for [x (range 1 (inc x))
        y (range 1 (inc y))]
    (field. (if (some #{[x y]} water) :water :land)
               x
               y
               nil)))

(def board (init-board board-side board-side))

(defn get-at [board x y]
  (nth board (+ (* 10 (dec y)) (dec x))))

(defn land? [ground]
  (= :land ground))

(defn besetzt? [board x y]
  (:token (get-at board x y)))

(defn set-token [board x y token]
  (assoc-in (get-at board 2 2) [:token] token))

;;write test for this
(defn del-token [board x y]
  (assoc (get-at board x y) :token nil))

;rethink this
(defn move-token [board x-orgin y-orgin x-target y-target]
  false)

(defn print-simple-board [board]
  (pprint (partition 10 (map #(if (land? (:ground %)) "L" "W")  board))))

