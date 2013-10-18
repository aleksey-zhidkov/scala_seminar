package actors2

import akka.actor.{ActorRef, Actor}

class ClientActor(initialMoney: Int, preferredDrinkName: String, barmen: ActorRef) extends Actor {

  var money = initialMoney

  def receive: ClientActor#Receive = {
    case "start" => {
      println("Send menu request")
      barmen ! MenuRequest
    }

    case menu: MenuResponse => {
      println(s"Menu received: ${menu.drinks}")
      val preferredDrink = menu.drinks.find {
        drink: DrinkType =>
          drink.name == preferredDrinkName && drink.cost <= money
      }

      preferredDrink match {
        case None => {
          println("Could not get drink")
          context.system.shutdown()
        }
        case Some(drinkType) => {
          money = money - drinkType.cost
          println("Send drink request")
          sender ! new DrinkRequest(drinkType, drinkType.cost)
        }
      }

    }

    case drink: DrinkResponse => {
      println(s"Drink $drink received")
      context.system.shutdown()
    }
  }

}
