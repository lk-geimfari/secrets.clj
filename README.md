[![](.github/logo.png)](https://github.com/lk-geimfari/secrets.clj)

A library designed to generate cryptographically strong random numbers suitable for managing data such as passwords, account authentication, security tokens, and related secrets.

The `secrets.clj` is a port of the [`secrets`](https://docs.python.org/3/library/secrets.html) 
module from Python's standard library for Clojure.


## Status

[![test](https://github.com/lk-geimfari/secrets.clj/actions/workflows/test.yml/badge.svg)](https://github.com/lk-geimfari/secrets.clj/actions/workflows/test.yml)
[![codecov](https://codecov.io/gh/lk-geimfari/secrets.clj/branch/master/graph/badge.svg)](https://codecov.io/gh/lk-geimfari/secrets.clj)
[![Clojars Version](https://img.shields.io/clojars/v/likid_geimfari/secrets?color=brightgreen)](https://clojars.org/likid_geimfari/secrets)
[![Cljdoc Badge](https://cljdoc.org/badge/likid_geimfari/secrets)](https://cljdoc.org/d/likid_geimfari/secrets/CURRENT)

## Installation

**Leiningen/Boot**:

Add the following dependency to your `project.clj` file:

```clojure
[likid_geimfari/secrets "2.1.0"]
```

**Clojure CLI/deps.edn**:

```clojure
likid_geimfari/secrets {:mvn/version "2.1.0"}
```

## Documentation

You can find the complete documentation on the [cljdoc](https://cljdoc.org/d/likid_geimfari/secrets/CURRENT).

## Usage examples
 
```clojure
user=> (secrets.core/token-hex 32)
"2aa5430064918acf140bb423678cef7353f7055597bc61305414c5371106ebef"

user=> (secrets.core/token-urlsafe 32)
"kfbGVrB6jz6hyOl_2rX9UIHgiop2-rM_jo2XEK7oTj0"

user=> (secrets.core/token-bytes 16)
#object["[B" 0x3b2454e9 "[B@3b2454e9"]

user=> (secrets.core/randbelow 100)
71

user=> (secrets.core/randbits 32)
1530556122

user=> (secrets.core/choice [8 16 32 64 128])
8

user=> (secrets.core/choices [8 16 32 64 128] 2)
(128 16)

user=> (secrets.tools/uuid4)
"84e9c5c0-ceb4-4aab-9a58-668f59b9a9e5"

user=> (secrets.tools/unix-timestamp)
1601927558
```

See [«Recipes and best practices»](#recipes-and-best-practices) for more usage examples.

## Recipes and best practices
This section shows recipes and best practices for using secrets to manage a basic level of security.

Generate an eight-character alphanumeric password:

```clojure
(ns example.security
  (:use [clojure.string :only [join]]
    [secrets.core]
    [secrets.constants :only [ascii-letters digits]]))

(defn generate-password [n]
  (join "" (secrets.core/choices (str ascii-letters digits)) n))
```

```clojure
example.security=> (generate-password 8)
"7gHY2N4s"
```

**Note**: Applications should not [store passwords in a recoverable format](http://cwe.mitre.org/data/definitions/257.html), 
whether plain text or encrypted. They should be salted and hashed using a cryptographically-strong one-way (irreversible) hash function.


Generate an [XKCD-style passphrase](https://xkcd.com/936/):

```clojure
(ns example.security
  (:use [secrets.core]
        [clojure.string :only [join lower-case split-lines]))

(def words
  (-> (slurp "/usr/share/dict/words")
      (split-lines)))

(defn generate-passphrase [n]
  (-> (join " " (secrets.core/choices words n))
      (lower-case)))
```

```clojure
example.security=> (generate-passphrase 5)
"uniaxally intercarrier straddleback basihyoid unhusk"
```

Generate a hard-to-guess temporary URL containing a security token suitable for password recovery applications:

```clojure
(ns example.security
  (:use [secrets.core :only [token-urlsafe]]))

(defn generate-password-recovery-url [n]
  (str "https://mydomain.com/reset=" (token-urlsafe n)))
```

```clojure
example.security=> (generate-password-recovery-url 32)
"https://mydomain.com/reset=3kOJuScK1mHyxXWnuMBAUQaIEdsBUluQBR-3Zlvv8XQ"
```

## License
MIT License. See [LICENSE](LICENSE) for more information.
