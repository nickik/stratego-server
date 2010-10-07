(ns stratego.test.board
  (:use [clojure.test]
        [stratego.board] :reload))

(deftest init-board-test
  (is (= (into {} (first (init-board 1 1)))
         {:ground :land, :x 1, :y 1, :token nil})))

(deftest get-at-test
  (is (= (into {} (get-at board 2 2))
         {:ground :land :x 2 :y 2 :token nil})))

(deftest land?-test
  (is (land? :land))
  (is (false? (land? :water))))

(deftest cfield-test
  (is (= (class (get-at board 1 1))
         (class (cfield :land 1 1 nil))))
  (is (= {:ground :land, :x 1, :y 1, :token nil}
         (into {} (cfield :land 1 1 nil)))))

(deftest besetzt?-test
  (is (nil? (besetzt? board 5 5)))
  (is (besetzt? [(cfield :land 5 5 "token")] 1 1)))

(deftest set-token-test
  (is  (= (set-token board 1 1 "token")
          (assoc board 0 (cfield :land 1 1 "token")))))

(deftest update-test
  (let [new-board (set-token (init-board 1 1) 1 1 "token")]
    (is (=  [(cfield :land 1 1 (seq "token"))]
            (update new-board 1 1 seq)))))

(deftest calc-pos-test
  (is (= (calc-pos 1 1) 0))
  (is (= (calc-pos 5 5) 44)))
