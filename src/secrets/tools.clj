(ns secrets.tools
  "This module contains useful utilities for dealing with secrets."
  {:author "Isaak Uchakaev"
   :last-update-date "01-01-2022"}
  (:require [clojure.string])
  (:import (java.time Instant)
           (java.util UUID)))

(defn unix-timestamp
  "Returns a number of seconds from the Unix epoch of 1970-01-01T00:00:00Z"
  [] (.getEpochSecond (Instant/now)))

(defn uuid4
  "Return UUID generated using a cryptographically strong pseudo random number generator."
  [] (str (UUID/randomUUID)))
