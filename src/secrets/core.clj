(ns secrets.core
  "Generate secure random numbers for managing secrets."
  (:refer-clojure :exclude [bytes])
  (:require [clojure.string :as string])
  (:import [java.security.SecureRandom]
           [java.util UUID]
           [java.util.Base64.Encoder]
           [org.apache.commons.codec.binary Base64 Base32 Hex]))

(defn uuid4
  "Return a UUID represents a 128-bit value."
  []  (.toString (UUID/randomUUID)))

(defn- bytes
  "Returns a random byte array of the specified size."
  [nbytes]
  (let [seed (byte-array nbytes)]
    (.nextBytes (SecureRandom.) seed) seed))

(defn token-bytes
  "Return a random byte string containing nbytes number of bytes. If nbytes is nil or not supplied,
  a reasonable default is used."
  [nbytes] (apply str (bytes nbytes)))


(defn token-hex
  "Return a random text string, in hexadecimal. The string has nbytes random bytes, each byte converted
  to two hex digits. If nbytes is nil or not supplied, a reasonable default is used."
  [nbytes] (String. (Hex/encodeHex ^bytes (bytes nbytes))))


(defn token-urlsafe
  "Return a random URL-safe text string, containing nbytes random bytes. The text is Base64 encoded,
  so on average each byte results in approximately 1.3 characters. If nbytes is nil or not supplied,
  a reasonable default is used."
  [nbytes] (println "Token Hex"))


(defn choice
  "Return a randomly-chosen element from a non-empty sequence."
  [sequence] (println "Choice"))

(defn randbelow
  "Return a random int in the range [0, n)."
  [n] (println "Choice"))

(defn randbits
  "Return an int with k random bits."
  [k] (println "Choice"))