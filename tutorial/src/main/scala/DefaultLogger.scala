trait DefaultLogger extends Logger {

  protected val prefix = "Constant"

  // а абстрактные методы можно реализовывать полямм
  protected val suffix = "Also constant"

}

object DefaultLogger {

  val threadWithDefaultLogger = new Thread() with DefaultLogger
  threadWithDefaultLogger.debug("Да, мы на лету создали чудо класс, который наследуется от потока и дефолтного логгера")
  // зуб не дам, но кажется компилятор сгенеряет под это дело новый класс и мы тут ещё и в производительности не потеряем

  // а ещё мона так:
  val pointWithLogger = new Point2D(2, 2) with Logger {

    protected val prefix: String = x.toString

    protected def suffix: String = getY.toString
  }

  pointWithLogger.debug("И у меня есть дебаг!")

}