(ns secrets.strings)

(def ascii-lowercase "abcdefghijklmnopqrstuvwxyz")
(def ascii-uppercase "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(def ascii-letters (str ascii-lowercase ascii-uppercase))
(def digits "0123456789")
(def hexdigits (str digits "abcdef" "ABCDEF"))
(def octdigits "01234567")
(def punctuation "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~")
