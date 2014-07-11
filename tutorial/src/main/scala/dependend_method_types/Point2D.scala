package dependend_method_types


object Point2D {

  type Point2D = Object {def apply(method: Method): method.type#signature}

  trait Method {
    type signature
  }

  object ToString extends Method {
    override type signature = () => String
  }

  object GetX extends Method {
    override type signature = () => Int
  }

  object SetX extends Method {
    override type signature = (Int) => Point2D
  }

  def Point2D(x: Int, y: Int): Point2D = {

    class Dispatch {

      def apply(method: Method): method.signature = (method match {
        case ToString => () => s"($x, $y)"
        case GetX => () => x
        case SetX => (x: Int) => Point2D(x, y)
      }).asInstanceOf[method.signature]

    }

    new Dispatch
  }

}


