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

(defn get-account [access-token account-id]
  (get-account-endpoint account-id "" {} access-token))

(defn get-account-categories [access-token account-id]
  (:categories
   (get-account-endpoint account-id "categories" {} access-token)))

(defn get-account-settings [access-token account-id]
  (get-account-endpoint account-id "settings" {} access-token))

(defn get-account-barcode [access-token account-id]
  (get-account-endpoint account-id "barcode" {} access-token))

(defn get-account-billing-info [access-token account-id]
  (get-account-endpoint account-id "billing-info" {} access-token))

(defn get-account-users [access-token account-id]
  (get-account-endpoint account-id "users" {} access-token))

(defn get-account-coversheet [access-token account-id]
  (get-account-endpoint account-id "coversheet" {} access-token))

(defn get-account-credit-cards [access-token account-id]
  (get-account-endpoint account-id "credit-cards" {} access-token))

(defn get-account-currencies [access-token account-id]
  (get-account-endpoint account-id "currencies" {} access-token))

(defn get-account-envelopes [access-token account-id]
  (get-account-endpoint account-id "envelopes" {} access-token))

(defn get-account-billing-invoices [access-token account-id]
  (get-account-endpoint account-id "billing/invoices" {} access-token))

(defn get-account-plan [access-token account-id]
  (get-account-endpoint account-id "plan" {} access-token))

(defn get-account-documents [access-token account-id options]
  (:documents
   (get-account-endpoint account-id "documents" options access-token)))

(defn- get-account-documents-by-type [type access-token account-id options]
  (account-documents access-token account-id (merge {:type type} options)))

(def get-account-receipts (partial get-account-documents-by-type "receipt"))
(def get-account-business-cards (partial get-account-documents-by-type "business-card"))
(def get-account-other-documents (partial get-account-documents-by-type "other"))
