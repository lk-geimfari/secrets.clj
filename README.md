
[![](.github/logo.png)](https://github.com/lk-geimfari/secrets.clj)


A Clojure library designed to generate secure random numbers for managing secrets

The secrets module is used for generating cryptographically strong random numbers suitable for managing data such 
as passwords, account authentication, security tokens, and related secrets.


# Usage
 
```clojure
user=> (secrets/token-hex 16)
'f9bf78b9a18ce6d46a0cd2b0b86df9da'

user=> (secrets/token-urlsafe 16)
'Drmhze6EPcv0fN_81Bj-nA'

user=> (secrets/token-bytes 16)
b'\xebr\x17D*t\xae\xd4\xe3S\xb6\xe2\xebP1\x8b'
```

Other functions:

```clojure
user=> (secrets/choice [0 4 5 2 1 2 3.3])
1
user=> (secrets/randbelow 256)
122
user=> (secrets/randbits 26)
60274314
```