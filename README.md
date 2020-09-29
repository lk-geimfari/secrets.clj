
[![](.github/logo.png)](https://github.com/lk-geimfari/secrets.clj)


A Clojure library designed to generate secure random numbers for managing secrets

The secrets module is used for generating cryptographically strong random numbers suitable for managing data such 
as passwords, account authentication, security tokens, and related secrets.


# Usage
 
```clojure
user=> (secrets.core/token-hex 32)
"2aa5430064918acf140bb423678cef7353f7055597bc61305414c5371106ebef"

user=> (secrets.core/token-urlsafe 32)
"kfbGVrB6jz6hyOl_2rX9UIHgiop2-rM_jo2XEK7oTj0"

user=> (secrets.core/token-bytes 16)
#object["[B" 0x3b2454e9 "[B@3b2454e9"]
```