[![](.github/logo.png)](https://github.com/lk-geimfari/secrets.clj)

[![Build Status](https://travis-ci.org/lk-geimfari/secrets.clj.svg?branch=master)](https://travis-ci.org/lk-geimfari/secrets.clj)

A Clojure library designed to generate secure random numbers for managing secrets. This project is a 
Python's `secret` module implementation for Clojure, based on Java's standard library.

The secrets module is used for generating cryptographically strong random numbers suitable for managing data such 
as passwords, account authentication, security tokens, and related secrets.

## Installation

To include one of the above libraries, for example ring-core, add the following to your `:dependencies:`

```
[secrets "0.1.2"]
```

## Recipes and best practices¶
This section shows recipes and best practices for using secrets to manage a basic level of security.

Generate an eight-character alphanumeric password:

```clojure
(ns security
  (:require secrets.core :as secrets))

(def alphabet (str ascii-letters digits))
(def password (clojure.string/join ", " (for [_ (range 32)] (choice alphabet))))
```
Generate a ten-character alphanumeric password with at least one lowercase character, at least one uppercase 
character, and at least three digits:

```
import string
import secrets
alphabet = string.ascii_letters + string.digits
while True:
    password = ''.join(secrets.choice(alphabet) for i in range(10))
    if (any(c.islower() for c in password)
            and any(c.isupper() for c in password)
            and sum(c.isdigit() for c in password) >= 3):
        break
```


Generate an XKCD-style passphrase:

```
import secrets
# On standard Linux systems, use a convenient dictionary file.
# Other platforms may need to provide their own word-list.
with open('/usr/share/dict/words') as f:
    words = [word.strip() for word in f]
    password = ' '.join(secrets.choice(words) for i in range(4))
```

Generate a hard-to-guess temporary URL containing a security token suitable for password recovery applications:

```
import secrets
url = 'https://mydomain.com/reset=' + secrets.token_urlsafe()
```

## Usage
 
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
