package interpreter

import scala.annotation.tailrec
import interpreter.ast.ExpressionNode

object REPL extends App {

  println("Type 'exit' to exit or arithmetic expression to evaluate")
  execLine()

  @tailrec
  def execLine() {
    val line = readLine(">")
    if ("exit" != line) {
      val parse: Parser.ParseResult[ExpressionNode[Int]] = Parser.parse(line)
      val output = parse match {
        case Parser.Success(ast: ExpressionNode[Int], _) => ast.eval()
        case Parser.Failure(message, _) => "Error: " + message
      }
      println(output)
      execLine()
    }
  }

}
