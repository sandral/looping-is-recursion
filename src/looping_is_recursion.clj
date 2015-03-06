(ns looping-is-recursion)

(defn power [base exp]
  (if (= exp 0)
    1
    (let [helper (fn [acc n k]
                   (if (= 1 k)
                     acc
                     (recur (* acc n) n (dec k))))]
      (helper base base exp))))


(defn last-element [a-seq]
    (let [[x & xs] a-seq]
      (if (empty? xs)
        x
        (last-element xs))))

(defn seq= [seq1 seq2]
  (if (= (count seq1) (count seq2))
    (let [[x & xs] seq1
          [y & ys] seq2]
      (if (and (empty? seq1) (empty? seq2))
        true
        (and (= x y) (seq= xs ys))))
    false))

(defn find-first-index [pred a-seq]
  (loop [acc a-seq n 0]
    (if (< n (count a-seq))
      (if (pred (first acc)) n
        (recur (next acc) (inc n)))
    nil)))

(defn avg [a-seq]
  (let [helper (fn [acc sum]
                 (if (empty? acc)
                   sum
                   (recur (rest acc) (+ sum (first acc)))))]
    (/ (helper a-seq 0) (count a-seq))))

(defn parity [a-seq]
  (set
   (keep
    (fn [[k v]]
      (when (odd? v) k))
    (frequencies a-seq))))

(defn fast-fibo [n]
  (loop [first 0 second 1 iter 0]
    (if (= iter n)
      first
      (recur second (+ first second) (inc iter)))))

(defn cut-at-repetition [a-seq]
  (loop [l #{} k [] a a-seq]
    (if (or (empty? a) (contains? l (first a))) k
      (recur (conj l (first a)) (conj k (first a)) (rest a)))))



