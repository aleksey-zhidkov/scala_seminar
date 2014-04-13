package interpreter

import scala.util.parsing.combinator._
import interpreter.ast.{ExpressionNode, LiteralNode, PlusNode, MinusNode, MultiplyNode, DivideNode}

object Parser extends JavaTokenParsers {

  def parse(text: String) = parseAll(expr, text)

  def expr: Parser[ExpressionNode[Int]] = binaryOp(term, "+" | "-")

  def term: Parser[ExpressionNode[Int]] = binaryOp(factor, "*" | "/")

  def factor: Parser[ExpressionNode[Int]] =
    "(" ~> expr <~ ")" |
      wholeNumber ^^ {
        x => LiteralNode(x.toInt)
      }

  def binaryOp(child: => Parser[ExpressionNode[Int]], ops: Parser[String]): Parser[ExpressionNode[Int]] =
    child ~ rep(ops ~ child) ^^ {
      case lhs ~ Nil => lhs
      case lhs ~ tail => build(lhs, tail)
    }

  val node = Map(
    "+" -> PlusNode _,
    "-" -> MinusNode _,
    "*" -> MultiplyNode _,
    "/" -> DivideNode _
  )

  def build(lhs: ExpressionNode[Int], rhs: List[String ~ ExpressionNode[Int]]): ExpressionNode[Int] = rhs match {
    case op ~ rhsOperand :: Nil => node(op)(lhs, rhsOperand)
    case op ~ rhsOperand :: tail => build(node(op)(lhs, rhsOperand), tail)
  }

}
