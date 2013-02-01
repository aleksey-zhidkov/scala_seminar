package msgs

sealed abstract class OperationResult

case class MultiplyResult(x: Int, y: Int, res: Int) extends OperationResult

case class DivideResult(x: Int, y: Int, res: Option[Int]) extends OperationResult