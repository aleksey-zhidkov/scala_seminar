package dsl

import xml.{PrettyPrinter, XML}

object AdvancedHtmlDslExample extends App {

  val page1Xml = XML.loadString(page1)
  val page2Xml = XML.loadString(page1)

  val pp = new PrettyPrinter(80, 2)
  println(pp.format(page1Xml))

  println()

  println(pp.format(page2Xml))

  def basePage(_body: TagContentElement) = html(

    head {
      tag("style")()(
        ".helloWorld { text-align: right}"
      )
    },

    body("class" -> "class")(

      div(style = "color: red") {
        plainText("My title")
      },

      _body

    )

  )

  def baseMenuPage(content: TagContentElement) = basePage(
    div()(
      div()("menu"),
      content
    )
  )

  def page1 = baseMenuPage {
    div()("page1")
  }

  def page2 = baseMenuPage {
    div()("page2")
  }

}
