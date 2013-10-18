package actors2

import akka.actor.Actor

class CoffeeMachineActor extends Actor {
  def receive: Actor.Receive = {
    case PrepareDrinkRequest(drinkType) => {
      println("Prepare drink request received")
      sender ! PreparedDrinkResponse(new Drink)
    }
  }

}
