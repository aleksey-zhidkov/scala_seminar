package dependend_method_types


object Point2D {

  type Point2D = Object {def apply(method: Method)(args: Any*): method.type#returnType}

  trait Method {
    type returnType
  }

  object ToString extends Method {
    override type returnType = String
  }

  object GetX extends Method {
    override type returnType = Int
  }

  object SetX extends Method {
    override type returnType = Point2D
  }

  def Point2D(x: Int, y: Int): Point2D = {

    class Dispatch {

      def apply(method: Method)(args: Any*): method.returnType = (method match {
        case ToString => s"($x, $y)"
        case GetX => x
        case SetX => Point2D(args.head.asInstanceOf[Int], y)
      }).asInstanceOf[method.returnType]

    }

    new Dispatch
  }

}


