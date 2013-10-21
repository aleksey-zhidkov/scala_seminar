package dependend_method_types

object Test extends App {

  import Point2D._

  val pnt: Point2D = Point2D(10, 10)
  val res: String = pnt(ToString)()
  println(res)
  println(pnt(GetX)())
  println(pnt(SetX)(20)(GetX)())

}
