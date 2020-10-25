(ns secrets.core-test
  (:require [clojure.test :refer [deftest, testing, is]]
            [secrets.core :as core]))

(deftest token-hex-test
  (testing "Generate a random hex string"
    (is (= (count (core/token-hex)) 64))
    (is (= (count (core/token-hex 64)) 128))))

(deftest token-bytes-test
  (testing "Generate the random bytes"
    (is (= (count (core/token-bytes)) 32))
    (is (= (count (core/token-bytes 128)) 128))))

(deftest token-urlsafe-test
  (testing "Generate a url-safe random string"
    (is (= (count (core/token-urlsafe)) 43))
    (is (= (count (core/token-urlsafe 16)) 22))))

(deftest uuid4-test
  (testing "Generate UUID4")
  (is (= (count (core/uuid4)) 36)))

(deftest randbelow-test
  (testing "Generate a random int in the range [0, n)")
  (let [number (core/randbelow 100)]
    (is (and (> number 0) (< number 100)))))

(deftest choice-test
  (testing "Choice a random element of coll")
  (let [chosen (core/choice [8 16 32 64 128])]
    (is (and (>= chosen 8) (<= chosen 128)))
    (is (thrown? java.lang.Exception (core/choice [])))))

(deftest unix-timestamp-test
  (testing "Returns UNIX-timestamp")
  (is (instance? java.lang.Long (core/unix-timestamp))))