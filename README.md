[![](.github/logo.png)](https://github.com/lk-geimfari/secrets.clj)

[![Build Status](https://travis-ci.org/lk-geimfari/secrets.clj.svg?branch=master)](https://travis-ci.org/lk-geimfari/secrets.clj)

A Clojure library designed to generate secure random numbers for managing secrets. This project is a 
Python's `secret` module implementation for Clojure, based on Java's standard library.

The secrets module is used for generating cryptographically strong random numbers suitable for managing data such 
as passwords, account authentication, security tokens, and related secrets.


## Recipes and best practices¶
This section shows recipes and best practices for using secrets to manage a basic level of security.

Generate an eight-character alphanumeric password:

```clojure
(ns somemodule
  (:require [secrets.core :as secrets]
            [secrets.strings :as strings]))

(def alphabet (str strings/ascii-letters strings/digits))
(defn generate-password [len]
  (clojure.string/join "" (repeat len (secrets/choice alphabet))))
```
Generate an XKCD-style passphrase:

```clojure
(def words
  "On standard Linux systems, use a convenient dictionary file.
  Other platforms may need to provide their own word-list."
  (-> (slurp "/usr/share/dict/words")
      (clojure.string/split-lines)))

(defn generate-passphrase [n]
  (-> (clojure.string/join " " (repeat n (choice words)))
      (clojure.string/lower-case)))
```

Generate a hard-to-guess temporary URL containing a security token suitable for password recovery applications:

```clojure
(ns somens
  (:require [secrets.core :as secrets]))

(def url (str "https://mydomain.com/reset=" (secrets/token-urlsafe)))
```

## API
 
```clojure
secrets.core => (token-hex 32)
"2aa5430064918acf140bb423678cef7353f7055597bc61305414c5371106ebef"

secrets.core => (token-urlsafe 32)
"kfbGVrB6jz6hyOl_2rX9UIHgiop2-rM_jo2XEK7oTj0"

secrets.core => (token-bytes 16)
#object["[B" 0x3b2454e9 "[B@3b2454e9"]

secrets.core => (randbelow 100)
71

secrets.core => (choice [8 16 32 64 128])
8
```

Additionally, you can generate `UUID4`:

```clojure
secrets.core => (uuid4)
"84e9c5c0-ceb4-4aab-9a58-668f59b9a9e5"
```

## License
MIT License. See [LICENSE](LICENSE) for more information.
