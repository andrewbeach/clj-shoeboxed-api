(ns shoeboxed-api.config)

(def sample-client
  {:id "46a81ad362564f47b359312a93be7923"
   :secret "85f1282b7c934b8ca675fb93b7babc6b"
   :redirect-uri "http://localhost:3000/sbx_auth/callback"})

(def sbx
  {:auth-uri "https://id.shoeboxed.com/oauth/authorize"
   :access-token-uri "https://id.shoeboxed.com/oauth/token"
   :api-uri "https://api.shoeboxed.com/v2/"})
