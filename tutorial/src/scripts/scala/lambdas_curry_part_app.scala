// в scala функции являются "first class citizens", т.е. они могут храниться в перменных, передаваться в качестве аргументов
// методам и возвращаться в качестве значений

// скала поддерживает лямбды (см. лямбда исчесление) - выражения, значениями которых являются функции
// где-то я находил статью, где было показано 4 способа объявлений лямбд, но щяс я её найти не могу,
// так что мы обойдёмся одним
var plus:(Int, Int) => Int = (x: Int, y: Int) => x + y

// plus - это функция, поэтому теперь мы можем написать так
var two = plus(1, 1)

// а ещё мы можем всех обмануть:
plus = (x: Int, y: Int) => x * y
two = plus(2, 1)

// каррирование и частичное применение - это понижение размерности функции на еденицу
// разница в том, что функция "curry" возвращает вам функцию от n - 1, аргумента, которая принимает функцию от одного аргумента
// и возвращает функцию от n - 2 аргументов
// функцция же "applyPartially" на вход получает функцию от n аргументов и возвращает функцию от n - 1 аргумента, которая уже
// возвращает значение. Т.е. разница в возвращаемом типе - в первом случае это функция, во втором - значение
def multiply(x: Int)(y: Int)(z: Int) = x * y * z
var sixteen = multiply(2)(2)(2)

val twice: Int => Int => Int = multiply(2)
sixteen = twice(2)(2)

val eights: Int => Int = twice(2)
sixteen = eights(2)


def multiply2(x: Int, y: Int, z: Int) = x * y * z
sixteen = multiply2(2, 2, 2)

val twice2: (Int, Int) => Int = multiply2(2, _: Int, _: Int)
sixteen = twice2(2, 2)

val eights2: Int => Int = twice2(2, _: Int)
sixteen = eights2(2)


// Частичные функцие имеют к каррированию и частичному применению только то отношение, что многие (если верить интерентам)
// и я в их числе путаю все эти три понятия между собой
// Частичная функция, это функция которая определена не на всём множестве её аргументов и в scala есть специальный тип для
// частичных функций, который определяет 2 метода: isDefinedAt и apply
def printResultIfDefined(arg: Int, f: PartialFunction[Int, Int]) {
  if (f.isDefinedAt(arg)) {
    println(f.apply(arg))
  }
}

// так же в scala поддерживает спец синтаксис для определения частичных функций:
def divide42ByX:PartialFunction[Int, Int] = { case x: Int if x != 0 =>  42 / x }

printResultIfDefined(42, divide42ByX)
printResultIfDefined(0, divide42ByX)
printResultIfDefined(-42, divide42ByX)