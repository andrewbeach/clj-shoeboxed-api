(ns shoeboxed-api.core
  (:require [shoeboxed-api.config :as config]
            [shoeboxed-api.util :refer [parse-body]]
            [clj-http.client :as http]))

;; API Helpers

(defn get-endpoint
  ([endpoint client] (get-endpoint endpoint client {}))
  ([endpoint client params]
   (let [res (http/get
               (str (:api-uri config/sbx) endpoint)
               {:oauth-token (:access-token client)
                :query-params params})]
     (parse-body res))))

;; Account Endpoints

(defn get-account-endpoint
  ([endpoint account-id client] (get-account-endpoint endpoint account-id client {}))
  ([endpoint account-id client params]
   (get-endpoint (format "accounts/%s/%s" account-id endpoint) client params)))

(def get-account (partial get-account-endpoint ""))
(def get-account-barcode (partial get-account-endpoint "barcode"))
(def get-account-billing-info (partial get-account-endpoint "billing-info"))
(def get-account-billing-invoices (partial get-account-endpoint "billing/invoices"))
(def get-account-categories (partial get-account-endpoint "categories"))
(def get-account-coversheet (partial get-account-endpoint "coversheet"))
(def get-account-credit-cards (partial get-account-endpoint "credit-cards"))
(def get-account-currencies (partial get-account-endpoint "currencies"))
(def get-account-documents (partial get-account-endpoint "documents"))
(def get-account-envelopes (partial get-account-endpoint "envelopes"))
(def get-account-plan (partial get-account-endpoint "plan"))
(def get-account-settings (partial get-account-endpoint "settings"))
(def get-account-users (partial get-account-endpoint "users"))
(def get-account-vendors (partial get-account-endpoint "vendors"))


;; Virtual Account Endpoints

(defn get-account-documents-by-type
  ([type account-id client] (get-account-documents-by-type type account-id client {}))
  ([type account-id client params]
   (get-account-documents account-id client (merge params {:type type}))))

(def get-account-business-cards (partial get-account-documents-by-type "business-card"))
(def get-account-other-documents (partial get-account-documents-by-type "other"))
(def get-account-receipts (partial get-account-documents-by-type "receipt"))


;; User Endpoints

(def get-user (partial get-endpoint "user"))
(def get-user-accounts (partial get-endpoint "user/accounts"))
(def get-user-applications (partial get-endpoint "user/applications"))
(def get-user-stats (partial get-endpoint "user/stats"))
