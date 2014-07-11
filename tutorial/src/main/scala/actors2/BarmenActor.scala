package actors2

import akka.actor.{ActorRef, Actor}
import akka.pattern.ask
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import akka.util.Timeout


class BarmenActor(coffeeMachine: ActorRef, cashbox: ActorRef) extends Actor {

  val menu = Seq(DrinkType("Americano", 110), DrinkType("Espresso", 120))

  def receive = {

    case MenuRequest => {
      println("Menu request received")
      sender ! MenuResponse(menu)
    }

    case DrinkRequest(drinkType, money) => {
      if (money < drinkType.cost) sender ! NotEnoughMoneyResponse(drinkType.cost - money)
      else {
        val client = sender

        implicit val timeout = Timeout(1000)

        val futureDrink = coffeeMachine ? PrepareDrinkRequest(drinkType)
        val futureCheck = cashbox ? PutRequest(money)

        (Await.result(futureDrink, Duration.apply(1, TimeUnit.SECONDS)),
          Await.result(futureCheck, Duration.apply(1, TimeUnit.SECONDS))) match {
          case (drink: PreparedDrinkResponse, check: PutResponse) => client ! DrinkResponse(drink.drink, check.check)
        }
      }
    }
  }

}
