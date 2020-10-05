[![](.github/logo.png)](https://github.com/lk-geimfari/secrets.clj)

[![Build Status](https://travis-ci.org/lk-geimfari/secrets.clj.svg?branch=master)](https://travis-ci.org/lk-geimfari/secrets.clj)

A Clojure library designed to generate secure random numbers for managing secrets. This project is a 
Python's `secret` module implementation for Clojure, based on Java's standard library.

The secrets module is used for generating cryptographically strong random numbers suitable for managing data such 
as passwords, account authentication, security tokens, and related secrets.

## API
 
```clojure
user=> (secrets.core/token-hex 32)
"2aa5430064918acf140bb423678cef7353f7055597bc61305414c5371106ebef"

user=> (secrets.core/token-urlsafe 32)
"kfbGVrB6jz6hyOl_2rX9UIHgiop2-rM_jo2XEK7oTj0"

user=> (secrets.core/token-bytes 16)
#object["[B" 0x3b2454e9 "[B@3b2454e9"]

user=> (secrets.core/randbelow 100)
71

user=> (secrets.core/choice [8 16 32 64 128])
8

user=> (secrets.core/uuid4)
"84e9c5c0-ceb4-4aab-9a58-668f59b9a9e5"
```

There are a namespace `secrets.strings` with useful constants:

```clojure
user=> secrets.strings/ascii-lowercase
"abcdefghijklmnopqrstuvwxyz"
user=> secrets.strings/ascii-uppercase
"ABCDEFGHIJKLMNOPQRSTUVWXYZ"
user=> secrets.strings/ascii-letters
"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
user=> secrets.strings/digits
"0123456789"
user=> secrets.strings/hexdigits
"0123456789abcdefABCDEF"
user=> secrets.strings/octdigits
"01234567"
user=> secrets.strings/punctuation
"!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~"
```

## Recipes and best practices
This section shows recipes and best practices for using secrets to manage a basic level of security.

Generate an eight-character alphanumeric password:

```clojure
(ns example.security)

(use '[clojure.string :only [join]])
(use '[secrets.core :only [choice]])
(use '[secrets.strings :only [ascii-letters digits]])

(defn generate-password [n]
  (join "" (repeatedly n #(choice (str ascii-letters digits)))))

```

the result will be:

```clojure
example.security=> (generate-password 8)
"7gHY2N4s"
```

**Note**: Applications should not [store passwords in a recoverable format](http://cwe.mitre.org/data/definitions/257.html), 
whether plain text or encrypted. They should be salted and hashed using a cryptographically-strong one-way (irreversible) hash function.


Generate an [XKCD-style passphrase](https://xkcd.com/936/):

```clojure
(ns example.security)

(use '[secrets.core :only [choice]])
(use '[secrets.strings :only [ascii-letters digits]])
(use '[clojure.string :only [join lower-case split-lines]])

(def words
  (-> (slurp "/usr/share/dict/words")
      (split-lines)))

(defn generate-passphrase [n]
  (-> (join " " (repeatedly n #(choice words)))
      (lower-case)))
```

the result will be:

```clojure
example.security=> (generate-passphrase 5)
"uniaxally intercarrier straddleback basihyoid unhusk"
```

Generate a hard-to-guess temporary URL containing a security token suitable for password recovery applications:

```clojure
(ns example.security)

(use '[secrets.core :only [token-urlsafe]])

(defn generate-reset-url [n]
  (str "https://mydomain.com/reset=" (token-urlsafe n)))
```

the result will be:

```clojure
example.security=> (generate-reset-url 32)
"https://mydomain.com/reset=3kOJuScK1mHyxXWnuMBAUQaIEdsBUluQBR-3Zlvv8XQ"
```

## License
MIT License. See [LICENSE](LICENSE) for more information.
