(ns shoeboxed-api.util
  (:require [cheshire.core :as cheshire]))

(defn parse-body [res]
  "get json decoded body of response"
  (cheshire/parse-string (:body res) true))
