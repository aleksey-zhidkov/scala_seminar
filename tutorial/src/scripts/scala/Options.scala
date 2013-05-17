// Опции - это обёртки значений которых может не быть
def getUserInput: Option[Int] = Console.readLine() match {
  case x if x.matches("^\\d*$") => Option(x.toInt)
  case _ => None
}

println("Пиши!")
for (i <- 1 to 2) {
  getUserInput match {
    case Some(x) => println(s"Число $x")
    case None => println("Не число")
  }

}

println()

// Options являются коллециями - Some коллекция из одного элемента, None - из 0, поэтому можно сделать так
var optionalValue: Option[String] = Some("Я есть")
optionalValue foreach println

optionalValue = None
optionalValue foreach println

println()

// Пример условного выполнения с опциями
def updateUserAndSendEmail(user: Option[String], email: Option[String]) {
  // required может быть null, но все на него ругаются

  user match {
    case Some(value) => println(s"Обновляем пользователя $value")
    case None => println("Создаём дефолтного пользователя")
  }

  email foreach {email: String =>
    println("Отправляем нотификацию на " + email)
  }

}

updateUserAndSendEmail(None, None)
println()

updateUserAndSendEmail(Option("User"), None)
println()

updateUserAndSendEmail(Option("User"), Option("test@test.com"))