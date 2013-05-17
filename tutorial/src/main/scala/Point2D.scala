
class Point2D(val x: Int, yVal: Int) {

  private var y = yVal

  // для геттеров не рекомендуется использовать пустые скобки
  def getY = y

  def setY(newY: Int) {
    y = newY
  }

}

object Point2D {

  def apply(x: Int, y: Int) = new Point2D(x, y)

  def main(args: Array[String]) {
    val pnt1 = new Point2D(1, 1)
    val pnt2 = Point2D(2, 2)

    // аналогичные штуки есть для списков и множеств
    val list = List(1, 2 ,3)
    val set = Set(1, 2, 3)

    // и даже мап!
    val map = Map(1 -> "one", 2 -> "two")
    // -> - это стандартный метод, который неявно добавляется ко всем классам и возвращет дву-местный кортеж

    println(pnt1.x) // x доступен, потому что объвлен с val (или var), а yVal - нет
    pnt1.getY // при доступе к геттерам так же не рекомендуется использовать скобки
    pnt1.y
  }

}