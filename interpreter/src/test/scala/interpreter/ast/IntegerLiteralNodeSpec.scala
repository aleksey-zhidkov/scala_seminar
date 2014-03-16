package interpreter.ast

import org.scalatest.{Matchers, WordSpec, GivenWhenThen}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.prop.TableDrivenPropertyChecks._

@RunWith(classOf[JUnitRunner])
class IntegerLiteralNodeSpec extends WordSpec with Matchers with GivenWhenThen {

  val literals =
    Table("literal",
      -100,
      -2,
      -1,
      0,
      1,
      2,
      10)

  forAll(literals) {
    literalValue: Int =>
    s"An IntegerLiteralNode with value $literalValue" when {

      "evaluated" should {

        "returns it's value" in {

          Given(s"IntegerLiteralNode with value $literalValue")
          val node = LiteralNode(literalValue)

          When("node evaluated")
          val value: Int = node.eval()

          Then(s"it value should be $literalValue")
          value should be === literalValue
        }

      }
    }
  }

}
