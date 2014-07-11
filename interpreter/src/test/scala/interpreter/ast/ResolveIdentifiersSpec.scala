package interpreter.ast

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}
import interpreter.mem.Variable

@RunWith(classOf[JUnitRunner])
class ResolveIdentifiersSpec extends WordSpec with Matchers {

  "An identifier node" when {

    val idNode = IdentifierNode("a")
    val bindings = Map("a" -> Variable(1))

    "resolved" should {

      val resolvedTree = resolveIdentifiers(idNode, bindings)

      "return an VariableNode wich evaluates to specified value" in {
        val value: Int = resolvedTree.eval()
        value should be(1)
      }

    }

  }

  "An expression with identifier" when {

    val tree = PlusNode(IdentifierNode[Int]("a"), PlusNode(LiteralNode(1), LiteralNode(1)))
    val bindings = Map("a" -> Variable(1))

    "resolved and evaluated" should {

      val resolvedTree = resolveIdentifiers(tree, bindings)
      val value: Int = resolvedTree.eval()

      "return correct value" in {
        value should be(3)
      }
    }

  }

}
