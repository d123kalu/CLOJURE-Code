(ns .Part1)

;Entering data into the hash map
(def table
  [{:product "Pencil"
    :city    "Toronto"
    :year    "2010"
    :sales   2653.00}
   {:product "Pencil"
    :city    "Oshawa"
    :year    "2010"
    :sales   525.00}
   {:product "Bread"
    :city    "Toronto"
    :year    "2010"
    :sales   136264.00}
   {:product "Bread"
    :city    "Oshawa"
    :year    "nil"
    :sales   242634.00}
   {:product "Bread"
    :city    "Ottawa"
    :year    "2011"
    :sales   426164.00}])

;Determines the cities where products are sold
(defn product->cities [product];receives the keyword to look for
  (get (into (sorted-map)
    (map (fn [name] (vector (keyword name)
      (map (fn [x] (get x :city)) (filter #(= (get % :product) name) table))));filters throught the table
        (distinct (map (fn [x] (get x :product)) table)))) (keyword product)));uses the keyword to look evaluate
(println (product->cities "Pencil"))
(println (product->cities "Bread"))


;Determines the city with the highest sale of each product
(defn product->bestcity []
  (into (sorted-map) (map (fn [name] (vector (keyword name)
    (first (first (take 1 (sort-by last > (map (fn [x] (vector (get x :city) (get x :sales)))
     (filter #(= (get % :product) name) table)))))))) (distinct (map (fn [x] (get x :product)) table)))))
(println (product->bestcity))


;Determinees the value to replace a nill element in the table
(defn table-without-missing-year []
    (assoc-in table [(first(filter #(nil? (get (get table %) :year))  (range 5))) :year]
      (str (first (first (sort-by last > (frequencies (map (fn [x] (get x :year));first determines the frequency of each year
        (filter #(not (nil? (get % :year)))  table)))))))));replaces any nill value with the highest occuring year
(println (table-without-missing-year))
