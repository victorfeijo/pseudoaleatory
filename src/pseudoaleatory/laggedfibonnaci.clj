(use 'criterium.core)
(ns pseudoaleatory.laggedfibonnaci)

(def l 55)
(def k 24)
(def M 32)

(defn initial-lfg-values
  "Retorna um array com os L primeiros valores
  aleatórios da sequência"
  ([]
   (initial-lfg-values []))
  ([created]
   (if (= (count created) l)
     created
     (recur (conj created (rand l))))))

(def fst-values (initial-lfg-values))

(defn lagged-fib-gen
  "Função recursiva que calcula o N valor da
  sequência laggged fibonnaci"
  [n]
  (if (< n l)
    (get fst-values n)
    (mod (+ (lagged-fib-gen (- n l))
            (lagged-fib-gen (- n k)))
         (Math/pow 2 M))))

(def rand-seed (atom l))

(defn next-rand
  "Calcula o próximo valor da sequência,
  inicializando em L, altera o estado para a
  próxima chamada ser L+1"
  []
  (lagged-fib-gen (int (swap! rand-seed inc))))

(next-rand)
; returns 15.501800887303068
(next-rand)
; returns 57.29627954962133
(next-rand)
; returns 82.09220051424658
(next-rand)
; returns 44.83535819945328
(next-rand)
; returns 57.49784770134884
