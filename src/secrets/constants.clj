(ns secrets.constants
  "This module contains useful constants for dealing with secrets."
  {:author "Isaak Uchakaev"
   :last-update-date "04-10-2022"})

(def digits "0123456789")
(def octdigits "01234567")
(def hexdigits (str digits "abcdef" "ABCDEF"))
(def ascii-lowercase "abcdefghijklmnopqrstuvwxyz")
(def ascii-uppercase "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(def punctuation "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~")
(def ascii-letters (str ascii-lowercase ascii-uppercase))
