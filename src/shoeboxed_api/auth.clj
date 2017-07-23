(ns shoeboxed-api.auth
  (:require [shoeboxed-api.config :as config]
            [shoeboxed-api.util :refer [parse-body]]
            [clj-http.client :as http]
            [cheshire.core :as cheshire]))

(defn initialize-client [client-id client-secret redirect-uri]
  {:id client-id
   :secret client-secret
   :redirect-uri redirect-uri})

(defn authorize [client authorization-code]
  "return (access-token, refresh-token)"
  (http/post (:access-token-uri config/sbx)
    {:form-params {:grant_type "authorization_code"
                   :code authorization-code
                   :redirect_uri (:redirect-uri client)}
     :basic-auth [(:id client) (:secret client)]}))

(defn authorize-client [client authorization-code]
  (let [auth-response (parse-body (authorize client authorization-code))]
    (assoc client
      :access-token (:access_token auth-response)
      :refresh-token (:refresh_token auth-response))))
