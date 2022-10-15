(defproject likid_geimfari/secrets "2.1.1"
  :description "A Clojure library designed to generate secure random numbers for managing secrets"
  :scm {:name "git"
        :url "https://github.com/lk-geimfari/secrets.clj"}
  :url "https://github.com/lk-geimfari/secrets.clj"
  :license {:name "MIT License"}
  :plugins [[lein-cljfmt "0.6.8"]
            [lein-cloverage "1.1.2"]
            [com.github.clj-kondo/lein-clj-kondo "0.2.1"]]
  :dependencies [[org.clojure/clojure "1.10.3"] 
                 [commons-codec/commons-codec "1.9"]]
  :deploy-repositories [["clojars" {:url "https://repo.clojars.org"
                                    :username :env/clojars_username
                                    :password :env/clojars_password
                                    :sign-releases false}]]
  :repl-options {:init-ns secrets.core})
