(ns shoeboxed-api.receipt)

(defn summing-receipts [sum r]
  (let [next-total (:total r)]
    (if next-total (+ sum next-total) sum)))

(defn sum [receipts]
  (reduce summing-receipts 0 receipts))

(defn group-by-vendor [receipts]
  (group-by :vendor receipts))

(defn group-by-last4 [receipts]
  (group-by #(get-in % [:paymentType :lastFourDigits]) receipts))

(defn list-last4 [receipts]
  (sort (keys (group-by-last4 receipts))))
