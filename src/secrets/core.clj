(ns secrets.core
  "A Clojure library designed to generate secure random numbers for managing secrets."
  (:require [clojure.string :as st])
  (:import [java.security SecureRandom]
           [org.apache.commons.codec.binary Base64 Hex]))

(defn- get-random-bytes
  "Returns a random byte array of the specified size."
  [nbytes]
  (let [seed (byte-array nbytes)]
    (.nextBytes (SecureRandom.) seed) seed))

(defn token-bytes
  "Return a random byte string containing nbytes number of bytes. If nbytes is nil or not supplied,
  a reasonable default is used."
  [nbytes] (get-random-bytes nbytes))

(defn token-hex
  "Return a random text string, in hexadecimal. The string has nbytes random bytes, each byte converted
  to two hex digits. If nbytes is nil or not supplied, a reasonable default is used."
  [nbytes] (String. (Hex/encodeHex ^bytes (get-random-bytes nbytes))))

(defn token-urlsafe
  "Return a random URL-safe text string, containing nbytes random bytes. The text is Base64 encoded,
  so on average each byte results in approximately 1.3 characters. If nbytes is nil or not supplied,
  a reasonable default is used."
  [nbytes] (-> (String. (Base64/encodeBase64 (get-random-bytes nbytes)))
               (st/replace "+" "-")
               (st/replace "/" "_")
               (st/replace "=" "")))
