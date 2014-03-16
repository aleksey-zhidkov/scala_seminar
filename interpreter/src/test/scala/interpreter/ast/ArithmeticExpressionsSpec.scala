package interpreter.ast

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{GivenWhenThen, Matchers, WordSpec}
import org.scalatest.prop.TableDrivenPropertyChecks._

@RunWith(classOf[JUnitRunner])
class ArithmeticExpressionsSpec extends WordSpec with Matchers with GivenWhenThen {

  val argumentsList =
    Table(("x", "y", "z", "res"),
      (2, 1, 3, 6),
      (0, 1, 3, 2),
      (0, 5, 3, -2),
      (0, 0, 0, 0)
    )

  forAll(argumentsList) {
    (x: Int, y: Int, z: Int, res: Int) =>

      s"An expression of x*x - y + z with values ($x, $y, $z)" when {

        "evaluated with arithmetic nodes" should {

          s"return $res" in {

            Given(s"Tree for expression")
            val node = PlusNode(
              MinusNode(
                MultiplyNode(LiteralNode(x), LiteralNode(x)),
                LiteralNode(y)),
              LiteralNode(z)
            )

            When("node evaluated")
            val value: Int = node.eval()

            Then(s"it value should be $res")
            value should be === res
          }

        }
      }
  }

}
