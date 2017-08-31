(use 'criterium.core)
(ns pseudoaleatory.blumblumshub)

(def primes {:p 11
             :q 19})

(defn get-m
  []
  (* (get primes :p) (get primes :q)))

(defn calc-rand
  [seed]
  (mod (Math/pow seed 2) (get-m)))

(def rand-seed (atom 3))

(defn next-bit
  [value sorted]
  (if (odd? sorted)
    (inc (bit-shift-left value 1))
    (bit-shift-left value 1)))

(defn bits-out
  ([bits]
   (bits-out bits 0 0))
  ([bits checked value]
   (let [sorted (int (swap! rand-seed calc-rand))]
     (if (= bits checked)
       value
       (recur bits (inc checked) (next-bit value sorted))))))

(defn next-rand
  ([]
   (next-rand 256))
  ([bits]
   (bits-out bits)))
