(ns secrets.tools-test
  (:require [clojure.test :refer [deftest, testing, is]]
            [secrets.tools]))

(deftest uuid4-test
  (testing "Generate UUID4")
  (is (= (count (secrets.tools/uuid4)) 36)))

(deftest unix-timestamp-test
  (testing "Returns UNIX-timestamp")
  (is (instance? java.lang.Long (secrets.tools/unix-timestamp))))