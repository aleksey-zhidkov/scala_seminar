trait Logger {

  // в трейтах можно объявлять абстрактные поля
  protected val prefix: String

  // а абстрактные методы можно реализовывать полямм
  protected def suffix: String

  def debug(msg: String) {
    println(prefix + "-" + msg + "-" + suffix)
  }

}
