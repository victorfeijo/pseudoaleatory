(use 'criterium.core)
(ns pseudoaleatory.lcg)

(def m (dec (Math/pow 2 31)))
(def a 48271)
(def c 0)
(def initial 1)

(defn calc-rand
  "Formula do lcg para calcular o valor aleatório."
  ([]
   (calc-rand rand-seed))
  ([n]
   (if (= n 0)
     initial
     (mod (+ (* a (calc-rand (dec n))) c) m))))

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
   (let [sorted (int (calc-rand checked))]
     (if (= bits checked)
       value
       (recur bits (inc checked) (next-bit value sorted))))))

(defn next-rand
  "Calcula o próximo valor aleatório."
  ([]
   (next-rand 30))
  ([bits]
   (bits-out bits)))
