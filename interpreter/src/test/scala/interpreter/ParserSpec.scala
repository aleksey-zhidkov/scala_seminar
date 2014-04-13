package interpreter

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, FunSuite}
import org.scalatest.prop.TableDrivenPropertyChecks._
import interpreter.ast.ExpressionNode

@RunWith(classOf[JUnitRunner])
class ParserSpec extends FunSuite with Matchers {

  val expressionsList =
    Table(("expression", "res"),
      ("1-2+1", 0),
      ("1+2-1", 2),
      ("1", 1),
      ("1+2*3", 7),
      ("1+2*2+1", 6),
      ("(1+2)*3", 9),
      ("1+(2*3)", 7)
    )

  forAll(expressionsList) {
    (expr: String, res: Int) =>

      test(expr) {
        val parse: Parser.ParseResult[ExpressionNode[Int]] = Parser.parse(expr)
        parse match {
          case Parser.Success(ast: ExpressionNode[Int], _) =>
            println(ast)
            ast.eval() should be(res)
          case Parser.NoSuccess(message, _) => throw new AssertionError(message)
        }
      }

  }

}