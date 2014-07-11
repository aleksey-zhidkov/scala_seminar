package object dsl {

  type TagContentElement = StringBuffer => Unit

  def tag(tagName: String)(attributes: (String, String)*)(body: TagContentElement*)(buffer: StringBuffer) {

    val attributesStr: String = buildAttributesStr(attributes: _*)

    buffer append s"<$tagName $attributesStr>"

    body.foreach(_(buffer))

    buffer append s"</$tagName>"
  }


  private def buildAttributesStr(attributes: (String, String)*): String = {
    val attributesStrs: Iterable[String] = attributes collect {
      case (attrName, attrValue) if attrValue.length > 0 => s"$attrName='$attrValue'"
    }

    val attributesStr = attributesStrs.mkString(" ")
    attributesStr
  }

  def plainText(text: String)(buffer: StringBuffer) {
    buffer.append(text)
  }

  implicit def String2TagBody(value : String): TagContentElement = plainText(value)

  def html(body: TagContentElement*): String = {
    val buffer: StringBuffer = new StringBuffer
    tag("html")()(body: _*)(buffer)
    buffer.toString
  }

  def body(attributes: (String, String)*)(body: TagContentElement*): TagContentElement = tag("body")(attributes : _*)(body: _*)

  def head(body: TagContentElement*): TagContentElement = tag("body")()(body: _*)

  def div(`class`: String = "", style: String = "")(body: TagContentElement*): TagContentElement = tag("div")("class" -> `class`, "style" -> style)(body: _*)

}
