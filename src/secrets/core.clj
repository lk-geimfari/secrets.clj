(ns secrets.core
  "The secrets module is used for generating cryptographically strong random numbers
  suitable for managing data such as passwords, account authentication, security tokens,
  and related secrets."
  (:require [clojure.string :as string])
  (:import [java.security SecureRandom]
           [java.util UUID]
           [org.apache.commons.codec.binary Base64 Hex]))

(defn- get-random-bytes
  "Returns a random byte array of the specified size."
  [nbytes]
  (let [bytes (byte-array nbytes)]
    (.nextBytes (SecureRandom.) bytes) bytes))

(defn uuid4
  "Return UUID generated using a cryptographically strong pseudo random number generator."
  []
  (.toString (UUID/randomUUID)))

(defn token-bytes
  "Return a random byte string containing nbytes number of bytes. If nbytes is nil or not supplied,
  a reasonable default is used."
  ([] (get-random-bytes 32))
  ([nbytes] (get-random-bytes nbytes)))

(defn- generate-token-hex
  [nbytes]
  (String. (Hex/encodeHex ^bytes (get-random-bytes nbytes))))

(defn token-hex
  "Return a random text string, in hexadecimal. The string has nbytes random bytes, each byte
  converted to two hex digits. If nbytes is nil or not supplied, a reasonable default is used (32)."
  ([] (generate-token-hex 32))
  ([nbytes] (generate-token-hex nbytes)))

(defn- generate-urlsafe-token
  [nbytes]
  (-> (String.
       (Base64/encodeBase64
        (get-random-bytes nbytes)))
      (string/replace "+" "-")
      (string/replace "/" "_")
      (string/replace "=" "")))

(defn token-urlsafe
  "Return a random URL-safe text string, containing nbytes random bytes. The text is Base64 encoded,
  so on average each byte results in approximately 1.3 characters. If nbytes is nil or not supplied,
  a reasonable default is used (32)"
  ([] (generate-urlsafe-token 32))
  ([nbytes] (generate-urlsafe-token nbytes)))

(defn randbelow
  "Return a random int in the range [0, n)."
  [n] (.nextInt (SecureRandom.) n))