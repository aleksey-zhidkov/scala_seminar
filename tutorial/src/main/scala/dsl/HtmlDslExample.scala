package dsl

import xml.{PrettyPrinter, XML}

object HtmlDslExample extends App {

  val xml = XML.loadString(buildHTML)

  val pp = new PrettyPrinter(80, 2)
  println(pp.format(xml))


  def buildHTML = html(

    head {
      tag("style")()(
        ".helloWorld { text-align: right}"
      )
    },

    body("class" -> "class")(

      div(style = "color: red") {
        plainText("Hello world!")
      },

      div(`class` = "helloWorld", style = "color: greed") {
        "Hello Implict!"
      }

    )

  )

}
