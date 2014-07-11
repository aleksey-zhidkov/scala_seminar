package actors2

class CoffeeMachineProtocolMessage

case class PrepareDrinkRequest(drink: DrinkType) extends CoffeeMachineProtocolMessage

case class PreparedDrinkResponse(drink: Drink) extends CoffeeMachineProtocolMessage