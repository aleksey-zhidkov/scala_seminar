import java.io.StringWriter

// в scala функции являются "first class citizens", т.е. они могут храниться в перменных, передаваться в качестве аргументов
// методам и возвращаться в качестве значений

// скала поддерживает лямбды (см. лямбда исчесление) - выражения, значениями которых являются функции
// при том есть 4 способа объявить лямбду (все эти объявления эквиваленты):
var plus:(Int, Int) => Int = (x: Int, y: Int) => x + y
val plus2 = { (a: Int, b: Int) => a + b }
// code style рекомендует использовать первые 2 вариант
val plus3 = (a: Int, b: Int) => a + b
val plus4 = (_: Int) + (_: Int)

// plus - это функция, поэтому теперь мы можем написать так
var two = plus(1, 1)

// а ещё мы можем всех обмануть:
plus = (x: Int, y: Int) => x * y
two = plus(2, 1)

// каррирование и частичное применение - это понижение размерности функции на еденицу
// разница в том, что функция "curry" возвращает вам функцию от n - 1, аргумента, которая принимает функцию от одного аргумента
// и возвращает функцию от n - 2 аргументов
// фунцоия же "aplayPartially" на вход получает функцию от n аргументов и возвращает функцию от n - 1 аргумента, которая уже
// возвращает значение. Т.е. разница в возвращаемом типе - в первом случае это функция, во втором - значение
def multiply(x: Int)(y: Int)(z: Int) = x * y * z
var sixteen = multiply(2)(2)(2)
val twice: Int => Int => Int = multiply(2)
sixteen = twice(2)(2)
val eights: Int => Int = twice(2)
sixteen = eights(2)

def multiply2(x: Int, y: Int, z: Int) = x * y * z
sixteen = multiply2(2, 2, 2)
var twice2: (Int, Int) => Int = multiply2(2, _: Int, _: Int)
sixteen = twice2(2, 2)
var eights2: Int => Int = twice2(2, _: Int)
sixteen = eights2(2)