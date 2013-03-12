for (i <- 0 to 100) {
  i match {
    case fuzzbuzz if i == 15 => println("fuzzbuzz")
    case fuzz if i % 3 == 0 => println("fuzz")
    case buzz if i % 5 == 0 => println("buzz")
    case _ => println(i)
  }
}