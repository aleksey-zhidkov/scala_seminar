class LazyInit {

  lazy val lazyString = lazyInit

  // а так низя:
  //lazy var lazyVar

  // для функций без аргументов не обязательно писать ()
  private def lazyInit: String = {
    println("Инициализация ленивой строки")
    "Ленивое значение"
  }

}

object LazyInit extends App {

  val lazyInit = new LazyInit
  println("Объект создан")
  println(lazyInit.lazyString)

}