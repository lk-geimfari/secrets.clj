(defproject secrets "0.1.0"
  :description "A Clojure library designed to generate secure random numbers for managing secrets"
  :license {:name "MIT License"}
  :main secrets.core
  :plugins [[lein-cljfmt "0.6.8"]]
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [commons-codec "1.6"]]
  :repl-options {:init-ns secrets.core})
