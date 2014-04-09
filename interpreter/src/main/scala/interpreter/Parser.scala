package interpreter

import scala.util.parsing.combinator._
import interpreter.ast.{ExpressionNode, LiteralNode, PlusNode, MinusNode, MultiplyNode, DivideNode}

object Parser extends JavaTokenParsers {

  def expr: Parser[ExpressionNode[Int]] =
    (term ~ "+" ~ term) ^^ {
      case lhs ~ plus ~ rhs => PlusNode(lhs, rhs)
    } |
      (term ~ "-" ~ term) ^^ {
        case lhs ~ minus ~ rhs => MinusNode(lhs, rhs)
      } |
      term

  def term: Parser[ExpressionNode[Int]] =
    (factor ~ "*" ~ factor) ^^ {
      case lhs ~ times ~ rhs => MultiplyNode(lhs, rhs)
    } |
      (factor ~ "/" ~ factor) ^^ {
        case lhs ~ div ~ rhs => DivideNode(lhs, rhs)
      } |
      factor

  def factor: Parser[ExpressionNode[Int]] =
    "(" ~> expr <~ ")" |
      wholeNumber ^^ {
        x => LiteralNode(x.toInt)
      }

  def parse(text: String) = parseAll(expr, text)

}
