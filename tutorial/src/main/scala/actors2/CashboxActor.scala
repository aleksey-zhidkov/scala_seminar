package actors2

import akka.actor.Actor

class CashboxActor extends Actor {

  private var balance = 0

  def receive: Actor.Receive = {

    case PutRequest(amount) => {
      balance = balance + amount
      println(s"Put money request received, balance = $balance")
      sender ! PutResponse(Check(amount))
    }

  }
}
