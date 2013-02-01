trait Logger {

  protected val prefix: String

  protected def suffix: String

  def debug(msg: String) {
    println(prefix + "-" + msg + "-" + suffix)
  }

}
