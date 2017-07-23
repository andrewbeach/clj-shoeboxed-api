(defproject org.clojars.andrewbeach/shoeboxed-api "0.1.0"
  :description "Wrapper for the public Shoeboxed HTTP API"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "3.6.1"]
                 [cheshire "5.7.1"]]
  :deploy-repositories [["clojars" {:sign-releases false}]])
