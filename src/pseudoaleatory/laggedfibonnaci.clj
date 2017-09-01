(use 'criterium.core)
(ns pseudoaleatory.laggedfibonnaci)

(def l 55)
(def k 24)
(def M 32)

(defn first-lag-values
  ([]
   (first-lag-values []))
  ([created]
   (if (= (count created) l)
     created
     (recur (conj created (rand l))))))

(def fst-values (first-lag-values))

(defn lagged-fib-gen
  [n]
  (mod (+ (get fst-values (- n l))
          (get fst-values (- n k)))
       (Math/pow 2 M)))
