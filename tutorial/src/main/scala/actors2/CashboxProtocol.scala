package actors2

class CashboxProtocolMessage

case class PutRequest(amount: Int)

case class PutResponse(check: Check)
