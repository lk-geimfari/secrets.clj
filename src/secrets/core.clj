(ns secrets.core
  "The secrets module is used for generating cryptographically strong random numbers
  suitable for managing data such as passwords, account authentication, security tokens,
  and related secrets."
  {:author "Isaak Uchakaev"
   :last-update-date "29-03-2021"}
  (:require [clojure.string])
  (:import (org.apache.commons.codec.binary Base64 Hex)
           (java.security SecureRandom)))

(def ^:private secure-random
  "Creates a secure random number generator (RNG)."
  (SecureRandom.))

(defn- get-random-bytes
  "Returns a random byte array of the specified size."
  [nbytes]
  (let [bytes (byte-array nbytes)]
    (.nextBytes secure-random bytes) bytes))

(defn token-bytes
  "Return a random byte string containing nbytes number of bytes.
  If nbytes is nil or not supplied, a reasonable default is used."
  ([] (get-random-bytes 32))
  ([nbytes] (get-random-bytes nbytes)))

(defn token-hex
  "Return a random text string, in hexadecimal.
  The string has nbytes random bytes, each byte converted to two hex digits.
  If nbytes is nil or not supplied, a reasonable default is used (32)."
  ([] (token-hex 32))
  ([nbytes] (Hex/encodeHexString (get-random-bytes nbytes))))

(defn token-urlsafe
  "Return a random URL-safe text string, containing nbytes random bytes.
  The text is Base64 encoded, so on average each byte results in approximately 1.3 characters.
  If nbytes is nil or not supplied, a reasonable default is used (32)."
  ([] (token-urlsafe 32))
  ([nbytes] (Base64/encodeBase64URLSafeString (get-random-bytes nbytes))))

(defn randbelow
  "Return a random int in the range [0, n)."
  [n] (.nextInt secure-random n))

(defn choice
  "Return a randomly-chosen element from a non-empty coll."
  [collection]
  (when (empty? collection)
    (throw (Exception. "Cannot choose from an empty sequence")))
  (nth collection (randbelow (count collection))))

(defn choices
  "Return a k sized list of elements chosen from the population with replacement.
  If the population is empty, raises an exception."
  [population k]
  (repeatedly k #(choice population)))
