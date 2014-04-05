package interpreter

import interpreter.mem.{Bindings, Variable}


package object ast {
  
  sealed trait ASTNode

  sealed trait ExpressionNode[T] extends ASTNode {

    def eval(): T

  }

  case class LiteralNode[T](value: T) extends ExpressionNode[T] {

    override def eval(): T = value

  }

  case class BinaryOpNode[LOP, ROP, RES](operator: (LOP, ROP) => RES, leftOperand: ExpressionNode[LOP], rightOperand: ExpressionNode[ROP]) extends ExpressionNode[RES] {

    override def eval(): RES = operator(leftOperand.eval(), rightOperand.eval())

  }

  def PlusNode(leftOperand: ExpressionNode[Int], rightOperand: ExpressionNode[Int]): ExpressionNode[Int] = BinaryOpNode[Int, Int, Int]({
    _ + _
  }, leftOperand, rightOperand)

  def MinusNode(leftOperand: ExpressionNode[Int], rightOperand: ExpressionNode[Int]): ExpressionNode[Int] = BinaryOpNode[Int, Int, Int]({
    _ - _
  }, leftOperand, rightOperand)

  def MultiplyNode(leftOperand: ExpressionNode[Int], rightOperand: ExpressionNode[Int]): ExpressionNode[Int] = BinaryOpNode[Int, Int, Int]({
    _ * _
  }, leftOperand, rightOperand)

  def DivideNode(leftOperand: ExpressionNode[Int], rightOperand: ExpressionNode[Int]): ExpressionNode[Int] = BinaryOpNode[Int, Int, Int]({
    _ / _
  }, leftOperand, rightOperand)

  def EqualsNode[OP](leftOperand: ExpressionNode[OP], rightOperand: ExpressionNode[OP]): ExpressionNode[Boolean] = BinaryOpNode[OP, OP, Boolean]({
    _ == _
  }, leftOperand, rightOperand)

  case class IdentifierNode[T](id: String) extends ExpressionNode[T] {

    override def eval(): T = throw new UnsupportedOperationException

  }

  case class VariableNode[T](variable: Variable[T]) extends ExpressionNode[T] {

    override def eval(): T = variable.value

  }

  def resolveIdentifiers[T](tree: ExpressionNode[_], bindings: Bindings): ExpressionNode[T] =
  tree match {
    case IdentifierNode(id) => VariableNode(bindings(id).asInstanceOf[Variable[T]])
    case BinaryOpNode(operator, leftOperand, rightOperand) =>
      BinaryOpNode(operator, resolveIdentifiers(leftOperand, bindings), resolveIdentifiers(rightOperand, bindings)).asInstanceOf[ExpressionNode[T]]
    case other: ExpressionNode[T] => other
  }

}
