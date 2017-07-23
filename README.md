# shoeboxed-api

A Clojure library wrapping the Shoeboxed v2 HTTP API. This is currently a hobby project, so there is no guarantee of api stability at this time. 

All endpoints returning data do so in the form of Clojure collections; when an HTTP endpoint returns a JSON response, you can expect a keywordized Clojure hash map. 

Since this is a hobby project, I will keep version numbers below 1.0.0 for the time being. If this becomes a working clojar, I will bump the version over 1.0.0 and promise api stability.

This is my first attempt at wrapping an http api in clojure, so please let me know if there are "best practices" that I'm not following here :-)

## Usage

The typical flow using this library is to: 

1. redirect your user to the Shoeboxed authentication uri `(:auth-uri shoeboxed-api.config/sbx)`
2. get the authorization_code from the query params when the user is redirected to `(:redirect-uri **your-client**)`
3. get an access token from `shoeboxed-api.auth/get-access-token`, given your client and authorization-code
4. make api calls using the client and access-token

Currently, the refresh token must be used manually, and there is no automated refresh. TODO: automate token refreshing when access token is expired.

To experiment with this in a clojure repl, you will need to manually call the Shoeboxed API oauth authentication endpoint in a browser `(:auth-uri shoeboxed-api.config/sbx)`, login as a real Shoeboxed user, and record the authorization code in the callback's query parameters. You can then use that code in the repl to gain an access token and make api calls. 

### Config

The config namespace contains default Shoeboxed API configuration, as well as helpers for building a Shoeboxed API client. 

`config/sbx` is always constant, as it defines the various uris encountered when interacting with the Shoeboxed API. 

`config/sample-client` shows the shape needed by a Shoeboxed API client. 

You will need to access your Shoeboxed account settings to create your own Shoeboxed API Application and redirect uri; after creating that, you will be given a client id and secret.

Currently, this library assumes that you will manually redirect your user to Shoeboxed's authentication uri, and that you can then get the authorization-code from the callback response. 

### OAuth

The authorization namespace has helper functions for obtaining an API access token after having the user authenticate (and thus return an authentication code). 

### API Endpoints

The api namespace contains functions for each Shoeboxed api endpoint, and they are named to match the HTTP API endpoints. 

**NOTE**: currently, only get endpoints are implemented.

For example, all `/account/{account_id}/xxx` HTTP endpoints have corresponding functions of the form `<verb>-account-xxx` that take the account id as a parameter, along with a client and options. 

The options are added directly to the query params for a GET request. 

So, GET `/account/{account_id}/categories?foo=bar` becomes `(get-account-categories account-id client {:foo "bar"})`.

To make API Endpoints, `client` must have a valid token at `:access-token`. 

All of these functions return the body of the endpoint's response as a clojure data structure; otherwise, the returned data exactly matches the response body declared by the HTTP API. 

### Receipt

The receipt namespace includes functions for working with Shoeboxed receipts. 

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
