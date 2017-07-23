(ns shoeboxed-api.auth
  (:require [shoeboxed-api.config :as config]
            [shoeboxed-api.util :refer [parse-body]]
            [clj-http.client :as http]
            [cheshire.core :as cheshire]))

;; (def access-token (atom ""))

(def account-id "680886300")

;; (defn get-authorization []
;;     (http/get (:base-uri config/sbx-client)
;;       {:query-params {"client_id" (:id sbx-client)
;;                       "response_type" "code"
;;                       "scope" "all"
;;                       "redirect_uri" (:redirect-uri sbx-client)
;;                       "state" "abc123"
;;                       }}))

(defn authorize [client authorization-code]
  "return (access-token, refresh-token)"
  (http/post (:access-token-uri config/sbx)
    {:form-params {:grant_type "authorization_code"
                   :code authorization-code
                   :redirect_uri (:redirect-uri client)}
     :basic-auth [(:id client) (:secret client)]}))

(defn get-access-token [client authorization-code]
  (:access_token
   (parse-body
     (authorize client authorization-code))))

;; (defn set-access-token [authorization-code]
;;   (reset! access-token (get-access-token authorization-code)))
