// map - это часто используемая функция-трансформер высшего порядка, которая реализована всеми коллекциями
val strings = List("1", "2", "3")

// здесь также приведён пример wildcard'а
val ints = strings map { Integer.parseInt(_) }

// reduce - функция-коллектор
val sum = ints reduce { _ + _ }

println(sum)

// так же есть штуки под названием foldLeft & foldRight

val left = ints.foldLeft("")({(total: String, x: Int) =>
  total + x
})
println(left)

val right = ints.foldRight("")({(x: Int, total: String) =>
  total + x
})
println(right)