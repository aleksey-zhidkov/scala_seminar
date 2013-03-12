for (i <- 0 to 100) {
  i match {
    case fizzbuzz if i % 15 => println("fizzbuzz")
    case fuzz if i % 3 == 0 => println("fizz")
    case buzz if i % 5 == 0 => println("buzz")
    case _ => println(i)
  }
}