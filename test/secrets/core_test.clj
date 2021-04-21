(ns secrets.core-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [secrets.core :as core]))

(deftest token-hex-test
  (testing "Generate a random hex string"
    (assert (= (count (core/token-hex)) 64))
    (assert (= (count (core/token-hex 64)) 128))))

(deftest token-bytes-test
  (testing "Generate the random bytes"
    (false? (string/includes? (core/token-bytes) "+"))
    (false? (string/includes? (core/token-bytes) "/"))
    (false? (string/includes? (core/token-bytes) "="))
    (assert (= (count (core/token-bytes)) 32))
    (assert (= (count (core/token-bytes 128)) 128))))

(deftest token-urlsafe-test
  (testing "Generate a url-safe random string"
    (assert (= (count (core/token-urlsafe)) 43))
    (assert (= (count (core/token-urlsafe 16)) 22))))

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
      (assert (= '() (core/choices [] 0)))
      (assert (= (count chosen) k)))))
