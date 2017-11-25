(ns task2)
(require '[clojure.java.io :as io])
(require '[clojure.string :as s])
(require '[clojure.core :as c])
(require 'clojure.set)

(defn after[]
  (let [rdr (io/reader "2017-10-15.txt")
        lines (line-seq rdr)
        pattern #"201([6-9])0([1-9]);([a-zA-Z0-9&%\-_., ]+);([a-zA-Z0-9&%\-_.,]+);CSCI ([a-zA-Z0-9&%\-_., ]+)"]
        (map #(get %1 0)
          (reduce into [] (map #(re-seq pattern %) lines)))))

(defn before[]
  (let [rdr (io/reader "2017-10-15.txt")
    lines (line-seq rdr)
      pattern #"20(0-1)(0-5)0([1-9]);([a-zA-Z0-9&%\-_., ]+);([a-zA-Z0-9&%\-_.,]+);CSCI ([a-zA-Z0-9&%\-_., ]+)"]
        (map #(get %1 0)
          (reduce into [] (map #(re-seq pattern %) lines)))))

(defn get-after []
  (distinct
      (sort
        (doall
          (for [x (sort (after))]
              (let [values (s/split x #";")]
                 (s/join "\t" [(nth values 3) (nth values 1)])))))))


(defn get-before []
  (distinct
    (sort
      (doall
       (for [x (sort (before))]
         (let [values (s/split x #";")]
           (s/join "\t" [(nth values 3) (nth values 1)])))))))

;(doall (for [i (get-all)]
;          (println i)))

(def a (get-after))
(def s (get-before))

(println "\nThese are the course created since 2015")
(defn D []
  (sort (clojure.set/difference (set a) (set s))))

 (doall (for [i (D)]
            (println i)))


(println "\nThese are the professors that taught the most lectures in 2016")

(defn readit[]
  (let [rdr (io/reader "2017-10-15.txt")
        lines (line-seq rdr)
        pattern #"2016([a-zA-Z0-9&\-_., ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'?\[\]]+);([a-zA-Z0-9&\-_.,\"()/'?\[\]]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);Lecture;([a-zA-Z0-9&\-_.,\"()/'? \[\] ]+);"]
        (map #(get %1 0)
          (reduce into [] (map #(re-seq pattern %) lines)))))

(defn get-prof []
      (sort
        (doall
          (for [x (sort (readit))]
              (let [values (s/split x #";")]
                 (s/join "\t" [(nth values 19)]))))))


(defn prof [] (c/frequencies (get-prof)))

(defn sortprof []
  (c/reverse (c/sort-by last (prof))))

(defn topten []
  (c/take 10 (sortprof)))

(doall (for [i (topten)]
         (println i)))
