(ns shoeboxed-api.api
  (:require [shoeboxed-api.config :as config]
            [shoeboxed-api.util :refer [parse-body]]
            [clj-http.client :as http]))

;; API Helpers

(defn get-endpoint [endpoint options access-token]
  (let [res (http/get
              (str (:api-uri config/sbx) endpoint)
              {:oauth-token access-token
               :query-params options})]
    (parse-body res)))

(defn- get-account-endpoint [account-id endpoint options access-token]
  (let [endpoint (format "accounts/%s/%s" account-id endpoint)]
    (get-endpoint endpoint options access-token)))

;;; API Endpoints

(defn account [access-token account-id]
  (get-account-endpoint account-id "" {} access-token))

(defn account-categories [access-token account-id]
  (:categories 
   (get-account-endpoint account-id "categories" {} access-token)))

(defn account-settings [access-token account-id]
  (get-account-endpoint account-id "settings" {} access-token))

(defn account-documents [access-token account-id options]
  (:documents
   (get-account-endpoint account-id "documents" options access-token)))

(defn- account-documents-by-type [type access-token account-id options]
  (account-documents access-token account-id (merge {:type type} options)))

(def account-receipts (partial account-documents-by-type "receipt"))
(def account-business-cards (partial account-documents-by-type "business-card"))
(def account-other-documents (partial account-documents-by-type "other"))
