package macros

object Foo {

  def bar(x: Int) = {
    Thread.sleep(200)
    x + 5
  }

}
