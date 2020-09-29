(ns secrets.core-test
  (:require [clojure.test :refer :all]
            [secrets.core :refer :all]))

(deftest token-hex-test
  (testing "Generate random hex string"
    (token-hex 32)))

(deftest token-bytes-test
  (testing "Generating random bytes"
    (token-bytes 32)))

(deftest token-urlsafe-test
  (testing "Generating url-safe random string"
    (token-urlsafe 32)))