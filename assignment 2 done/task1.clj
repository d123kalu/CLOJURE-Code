(ns task1)
(require '[clojure.java.io :as io])
(require '[clojure.string :as s])

(defn read[]
  (let [rdr (io/reader "2017-10-15.txt")
        lines (line-seq rdr)
        pattern #"201709;([a-zA-Z0-9&%\-_., ]+);([a-zA-Z0-9&%\-_.,]+);CSCI ([a-zA-Z0-9&%\-_., ]+)"]
        (map #(get %1 0)
          (reduce into [] (map #(re-seq pattern %) lines)))))

(defn get-unique-courses []
  (distinct
      (sort
        (doall
          (for [x (sort (read))]
              (let [values (s/split x #";")]
                 (s/join "\t" [(nth values 3) (nth values 1)])
                 ))))))

(doall (for [i (get-unique-courses)]
          (println i)))
