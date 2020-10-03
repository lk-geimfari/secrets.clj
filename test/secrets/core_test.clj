(ns secrets.core-test
  (:require [clojure.test :refer :all]
            [secrets.core :refer :all]))

(deftest token-hex-test
  (testing "Generate random hex string"
    (is (= (count (token-hex)) 64))
    (is (= (count (token-hex 64)) 128))))

(deftest token-bytes-test
  (testing "Generating random bytes"
    (is (= (count (token-bytes)) 32))
    (is (= (count (token-bytes 128)) 128))))

(deftest token-urlsafe-test
  (testing "Generating url-safe random string"
    (is (= (count (token-urlsafe)) 43))
    (is (= (count (token-urlsafe 16)) 22))))

(deftest uuid4-test
  (testing "Generating UUID4")
  (is (= (count (uuid4)) 36)))