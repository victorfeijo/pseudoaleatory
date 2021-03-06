(use 'criterium.core)
(ns pseudoaleatory.blumblumshub)

(def primes {:p 11
             :q 19})

(defn get-m
  "Calculo do produto dos primos."
  []
  (* (get primes :p) (get primes :q)))

(defn calc-rand
  "Formula do blum blum shub para calcular o valor
  aleatório a partir da seed."
  [seed]
  (mod (Math/pow seed 2) (get-m)))

(def rand-seed (atom 3))

(defn next-bit
  "Retorna o valor em bit referente ao aleatório,
  caso o valor seja ímpar é shiftado 1 e caso contrário 0."
  [value sorted]
  (if (odd? sorted)
    (inc (bit-shift-left value 1))
    (bit-shift-left value 1)))

(defn bits-out
  "Calcula o valor aleatório em um determinado número de bits,
  a cada bit dado para o valor final, o estado do seed é alterado."
  ([bits]
   (bits-out bits 0 0))
  ([bits checked value]
   (let [sorted (int (swap! rand-seed calc-rand))]
     (if (= bits checked)
       value
       (recur bits (inc checked) (next-bit value sorted))))))

(defn next-rand
  "Calcula o próximo valor aleatório."
  ([]
   (next-rand 256))
  ([bits]
   (bits-out bits)))
