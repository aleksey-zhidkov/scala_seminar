// Пример импорта высего пакета
import msgs._

// кейс классы - это обычные классы, для которых
// 1) компилятор "экспортирует" параметры
// 2) поддерживается рекурсивная декомпозиция в паттерн матчинге
// 3) компилятор генерирует equals и toString

println(exec(Divide(2, 2)))

def exec(operation: MathOperation) = operation match {
  case Multiply(x, y) => x * y
  case Divide(x, y) => x / y
  // так нельзя case Plus(x, y) => x + y
  case plus: Plus => 0 // plus.x,y тоже не доступны
}

println(exec(Divide(2, 2)))
