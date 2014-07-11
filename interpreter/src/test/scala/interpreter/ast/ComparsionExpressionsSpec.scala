package interpreter.ast

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{GivenWhenThen, Matchers, WordSpec}
import org.scalatest.prop.TableDrivenPropertyChecks._

@RunWith(classOf[JUnitRunner])
class ComparsionExpressionsSpec extends WordSpec with Matchers with GivenWhenThen {

  val argumentsList =
    Table(("a", "b", "c", "d", "res"),
      (2, 1, 1, 1, true),
      (0, 1, 3, 2, false),
      (1, 5, 0, 5, true),
      (0, 0, 0, 1, false)
    )

  forAll(argumentsList) {
    (a: Int, b: Int, c: Int, d: Int, res: Boolean) =>

      s"An expression of a * b = c + d with values ($a, $b, $c, $d)" when {

        "evaluated with boolean nodes" should {

          s"return $res" in {

            Given(s"Tree for expression")
            val node = EqualsNode(
              MultiplyNode(
                LiteralNode(a),
                LiteralNode(b)
              ),
              PlusNode(
                LiteralNode(c),
                LiteralNode(d)
              )
            )

            When("node evaluated")
            val value = node.eval()

            Then(s"it value should be $res")
            value should be (res)
          }

        }
      }
  }

}
