package actors2

import akka.actor.{Props, ActorSystem}

object Bar extends App {
  
  val system = ActorSystem("Bar")

  val coffeeMachine = system.actorOf(Props[CoffeeMachineActor], name = "coffeeMachine")
  val cashbox = system.actorOf(Props[CashboxActor], name = "cashbox")
  val barmen = system.actorOf(Props(classOf[BarmenActor], coffeeMachine, cashbox), name = "barmen")
  val client = system.actorOf(Props(classOf[ClientActor], 250, "Espresso", barmen), name = "client")

  // start the calculation
  client ! "start"
  
}
