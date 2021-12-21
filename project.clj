(defproject clojure_alpha_beta "0.2.0"
  :description "Generic Alpha-Beta Pruning library written in Clojure and ClojureScript."
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]
  :aot [clojure-alpha-beta.core]
  :main clojure-alpha-beta.core)
