package msgs

sealed abstract class MathOperation

case class Multiply(x: Int, y: Int) extends MathOperation

case class Divide(x: Int, y: Int) extends MathOperation

class Plus(x: Int, y: Int) extends MathOperation