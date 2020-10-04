(ns secrets.core-test
  (:require [clojure.test :refer :all]
            [secrets.core :refer :all]))

(deftest token-hex-test
  (testing "Generate a random hex string"
    (is (= (count (token-hex)) 64))
    (is (= (count (token-hex 64)) 128))))

(deftest token-bytes-test
  (testing "Generate the random bytes"
    (is (= (count (token-bytes)) 32))
    (is (= (count (token-bytes 128)) 128))))

(deftest token-urlsafe-test
  (testing "Generate a url-safe random string"
    (is (= (count (token-urlsafe)) 43))
    (is (= (count (token-urlsafe 16)) 22))))

(deftest uuid4-test
  (testing "Generate UUID4")
  (is (= (count (uuid4)) 36)))

(deftest randbelow-test
  (testing "Generate a random int in the range [0, n)")
  (let [number (randbelow 100)]
    (is (and (> number 0) (< number 100)))))

(deftest choice-test
  (testing "Choice a random element of coll")
  (def vector [8 16 32 64 128])
  (let [chosen (choice vector)]
    (is (and (>= chosen 8) (<= chosen 128)))
    (is (thrown? java.lang.Exception (choice [])))))
