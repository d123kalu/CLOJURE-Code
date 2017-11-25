(ns .Part2)

(defn pow [n] (* n n));Multiplies the number passed by itself

;estimates pi
(defn estimate-pi [n]
  (float (* 4 (/ (count (filter #(< % 1)
    (take n (repeatedly (fn []  (+ (pow (rand)) (pow (rand)))))))) n)))
)

(println (estimate-pi 1000));prints out the estimate of pi based off the number passed
