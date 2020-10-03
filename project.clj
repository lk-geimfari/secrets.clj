(defproject secrets "0.1.1"
  :description "A Clojure library designed to generate secure random numbers for managing secrets"
  :scm {:name "git"
        :url "https://github.com/lk-geimfari/secrets"}
  :url "https://github.com/lk-geimfari/secrets"
  :license {:name "MIT License"}
  :plugins [[lein-cljfmt "0.6.8"]]
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [commons-codec "1.6"]]
  :repl-options {:init-ns secrets.core})
