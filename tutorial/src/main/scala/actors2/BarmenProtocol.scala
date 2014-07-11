package actors2

class BarmenProtocolMessage

case object MenuRequest extends BarmenProtocolMessage

case class MenuResponse(drinks: Seq[DrinkType])

case class DrinkRequest(drink: DrinkType, money: Int) extends BarmenProtocolMessage

case class DrinkResponse(drink: Drink, check: Check) extends BarmenProtocolMessage

case class NotEnoughMoneyResponse(amount: Int) extends BarmenProtocolMessage