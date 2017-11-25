(ns task3)

(require '[clojure.java.io :as io])
(require '[clojure.string :as s])
(require '[clojure.core :as c])

(defn listone[]
  (let [rdr (io/reader "2017-10-15.txt")
        lines (line-seq rdr)
        pattern #"([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'?\[\]]+);([a-zA-Z0-9&\-_.,\"()/'?\[\]]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);Lecture;([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);"]
        (map #(get %1 0)
          (reduce into [] (map #(re-seq pattern %) lines)))))




(defn stuff []
  (doall (for [i (listone)]
    (let [values (s/split i #";")]
          (doall (for [j (listone)]
            (let [v (s/split j #";")]
                  (if (= (nth values 0) (nth v 0))
                      (if (= (nth values 6) (nth v 6))
                          (if (= (nth values 14) (nth v 14))
                              (if (= (nth values 15) (nth v 15))
                                  (if (c/not= (nth values 19) (nth v 19))
                                      (if (= (nth values 12) (nth v 10))
                                          (s/join "\t" [(nth values 19) (nth v 19)])
                                        )
                                  )
                              )
                          )
                      )
                  )
            )))
        ) )))

(defn prof [] (c/frequencies (stuff)))

(defn sortFREQ []
  (c/reverse (c/sort-by last (prof))))

(defn topten []
  (c/take 10 (sortFREQ)))


(doall (for [i (topten)]
        (println i)))
