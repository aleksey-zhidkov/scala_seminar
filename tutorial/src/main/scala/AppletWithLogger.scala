import java.applet.Applet

object AppletWithLogger extends Applet with Logger {

  private var msgNum = 0

  protected val prefix: String = "Constant"

  protected def suffix: String = {
    val res = msgNum
    msgNum += 1
    res.toString
  }

  // обратите внимание на ключевое слово override
  override def init() {
    debug("Да я знаю, что апплеты давно умерли")
  }
}
