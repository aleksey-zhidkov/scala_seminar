package actors

import akka.actor.{ActorRef, ActorSystem, Props, Actor}
import java.util.concurrent.TimeUnit
import msgs._
import collection.mutable.ListBuffer
import msgs.MultiplyResult
import msgs.DivideResult
import msgs.Multiply
import msgs.Divide
import scala.Some

class CalculatorActor extends Actor {

  def receive = {
    // можно и такое чудо - "⇒" можно использовать
    case Multiply(x, y) ⇒ {
      val res = x * y
      Thread.sleep(TimeUnit.SECONDS.toMillis(res))
      println(s"I'm ${this.toString} and i multiply $x and $y in thread ${Thread.currentThread()}")

      sender ! MultiplyResult(x, y, res)
    }

    case Divide(x, y) if y == 0 ⇒ {
      println(s"I'm ${this.toString} and i divide by 0 in thread ${Thread.currentThread()}")

      sender ! DivideResult(x, y, None)
    }

    case Divide(x, y) if y != 0 ⇒ {
      println(s"I'm ${this.toString} and i divide $x and $y in thread ${Thread.currentThread()}")

      val res = x / y
      sender ! DivideResult(x, y, Some(res))
    }
  }
}

class CommanderActor extends Actor {

  val pairs: List[(Int, Int)] = List((0, 0), (1, 1), (2, 2), (3, 3))
  val results: ListBuffer[Option[Int]] = new ListBuffer[Option[Int]]

  def receive = {
    case "Start" => {
      pairs.foreach({
        case (x, y) => {
          val oper = getOperation(x, y)
          val actor: ActorRef = context.actorOf(Props[CalculatorActor])
          actor ! oper
          println(s"$x and $y was sent")
        }
      })
    }

    case MultiplyResult(x, y, res) => {
      println(s"$x * $y = $res")
      processResult(Some(res))
    }

    case DivideResult(x, y, res) => {
      println(s"$x / $y = ${res.getOrElse("NaN")}")
      processResult(res)
    }
  }

  def getOperation(x: Int, y: Int): MathOperation =
    if (x % 2 == 0)
      Divide(x, y)
    else
      Multiply(x, y)

  def processResult(res: Option[Int]) {
    results.append(res)
    if (results.size == pairs.size) {
      println("All responses recieved, stop")
      context.system.shutdown()
    }
  }
}

object ActorsApp {

  def main(args: Array[String]) {

    // Create an Akka system
    val system = ActorSystem("Calc")

    // create the result listener, which will print the result and shutdown the system
    val commander = system.actorOf(Props[CommanderActor], name = "commander")

    // start the calculation
    commander ! "Start"
  }
}