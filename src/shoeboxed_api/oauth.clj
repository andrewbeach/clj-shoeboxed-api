(ns shoeboxed-api.oauth)

(defrecord OauthCredentials [consumer
                             ^String access-token
                             ^String access-token-secret])
