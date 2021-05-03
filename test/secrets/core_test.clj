(ns secrets.core-test
  (:require [clojure.test :refer [deftest testing is are]]
            [clojure.string :as str]
            [secrets.core :as core]))

(deftest token-hex-test
  (testing "Generate a random hex string"
    (assert (= (count (core/token-hex)) 64))
    (assert (= (count (core/token-hex 64)) 128))))

(deftest token-bytes-test
  (testing "Generate the random bytes"
    (are [char] (false? (str/includes? (core/token-bytes) char)) "+" "/" "=")
    (are [nbytes] (= (count (core/token-bytes nbytes)) nbytes) 8 64 256 1024)))

(deftest token-urlsafe-test
  (testing "Generate a url-safe random string"
    (are [nbytes result] (= (count (core/token-urlsafe nbytes)) result)
      32 43
      64 86
      16 22)))

(deftest randbelow-test
  (testing "Generate a random int in the range [0, n)"
    (let [number (core/randbelow 100)]
      (assert (and (> number 0) (< number 100))))))

(deftest choice-test
  (testing "Choice a random element of coll"
    (let [chosen (core/choice [8 16 32 64 128])]
      (assert (and (>= chosen 8) (<= chosen 128)))
      (is (thrown? Exception (core/choice []))))))

(deftest choices-test
  (testing "Choices a random elements of the collection"
    (let [k 3 chosen (core/choices [8 16 32 64 128] k)]
      (assert (empty? (core/choices [] 0)))
      (assert (= (count chosen) k)))))
