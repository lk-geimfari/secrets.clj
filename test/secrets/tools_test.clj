(ns secrets.tools-test
  (:require [clojure.test :refer [deftest testing]]
            [secrets.tools])
  (:import (java.util UUID)))

(deftest uuid4-test
  (testing "Generate UUID4"
    (assert (= (count (secrets.tools/uuid4)) 36))
    (assert (string? (secrets.tools/uuid4)))
    (assert (uuid? (UUID/fromString (secrets.tools/uuid4))))))

(deftest unix-timestamp-test
  (testing "Returns UNIX-timestamp"
    (assert (int? (secrets.tools/unix-timestamp)))
    (assert (instance? Long (secrets.tools/unix-timestamp)))))