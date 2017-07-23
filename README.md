# shoeboxed-api

A Clojure library wrapping the Shoeboxed v2 HTTP API. 

## Usage

### OAuth

### API Endpoints

There are functions for each api endpoint, and they are named to match the HTTP API endpoints. 

For example, all "/account/{account_id}/xxx" HTTP endpoints have corresponding functions "<verb>-account-xxx" that take the account id as a parameter, along with an access token and options. 

The options are added to directly to the query params for a GET request. 

So, GET "/account/{account_id}/categories?foo=bar" becomes "(get-account-categories access-token account-id {:foo "bar"})".

The order of arguments of these functions is intentional: 

- the first argument is always the access-token so that the functions can be partially applied for a given user's authentication context.
- the second argument, for qualified routes like "accounts/{account_id}", is that qualification variable (account id in this case), so that functions could be further applied for the local account context.
- an options parameter can be passed in containing key-value pairs matching the Shoeboxed API options for that endpoint. Options will be merged into pre-defined options in many cases. Ex: get-account-receipts merges its options parameter with {:type "receipts"}, so that, in addition to the given options, the result is also limited to receipts.

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
