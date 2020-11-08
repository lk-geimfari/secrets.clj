(defproject likid_geimfari/secrets "0.2.1"
  :description "A Clojure library designed to generate secure random numbers for managing secrets"
  :scm {:name "git"
        :url "https://github.com/lk-geimfari/secrets.clj"}
  :url "https://github.com/lk-geimfari/secrets.clj"
  :license {:name "MIT License"}
  :plugins [[lein-cljfmt "0.6.8"] [lein-cloverage "1.1.2"]]
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [commons-codec "1.6"]]
  :repl-options {:init-ns secrets.core})
