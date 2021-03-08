(ns secrets.strings)

(def digits "0123456789")
(def octdigits "01234567")
(def hexdigits (str digits "abcdef" "ABCDEF"))
(def ascii-lowercase "abcdefghijklmnopqrstuvwxyz")
(def ascii-uppercase "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(def punctuation "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~")
(def ascii-letters (str ascii-lowercase ascii-uppercase))
