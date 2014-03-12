package azhidkov

package object ast {
  
  sealed trait ASTNode
  
  sealed trait ExpressionNode {
    
    def eval(): Int
    
  }
  
  case class IntegerLiteralNode(value: Int) extends ASTNode with ExpressionNode {
    
    override def eval(): Int = value
    
  }
  
  case class ArithmeticNode(operator: (Int, Int) => Int, leftOperand: ExpressionNode, rightOperand: ExpressionNode) extends ASTNode with ExpressionNode {
    
    override def eval(): Int = operator(leftOperand.eval(), rightOperand.eval())
    
  }

  def PlusNode(leftOperand: ExpressionNode, rightOperand: ExpressionNode) = ArithmeticNode({_ + _}, leftOperand, rightOperand)

  def MinusNode(leftOperand: ExpressionNode, rightOperand: ExpressionNode) = ArithmeticNode({_ - _}, leftOperand, rightOperand)

  def MultiplyNode(leftOperand: ExpressionNode, rightOperand: ExpressionNode) = ArithmeticNode({_ * _}, leftOperand, rightOperand)

  def DevideNode(leftOperand: ExpressionNode, rightOperand: ExpressionNode) = ArithmeticNode({_ / _}, leftOperand, rightOperand)

}
