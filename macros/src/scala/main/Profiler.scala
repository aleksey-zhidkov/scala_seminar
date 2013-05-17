import collection.mutable

import language.experimental.macros

import reflect.macros.Context

object Profiler {

  val times = new mutable.HashMap[String, Long]()
  val starts = new mutable.HashMap[String, Long]()

  def start(method: String) = {
    println("start")
    starts.put(method, System.currentTimeMillis())
  }

  def stop(method: String) = {
    println("stop")
    val currentTime: Long = System.currentTimeMillis()
    times.put(method, times.get(method).getOrElse(0L) + currentTime - starts.remove(method).getOrElse(currentTime))
  }

  def stat() {
    for ((method, time) <- times) {
      println(s"$method : $time")
    }
  }

  def profile[A, R](f: (A => R), x: A): R = macro profileMacro[A, R]

  def profileMacro[A, R](c: Context)(f: c.Expr[(A => R)], x: c.Expr[A]): c.Expr[R] = {
    import c.universe._

    if (System.getProperty("enable.profiling") == "true") {
      val name = f.tree.children.last.children.drop(1).head.toString()

      val nameTree = Literal(Constant(name))
      val exprExpr = c.Expr[String](nameTree)
      reify {
        {
          start(exprExpr.splice)
          val res = f.splice.apply(x.splice)
          stop(exprExpr.splice)
          res
        }
      }
    } else {
      reify(f.splice.apply(x.splice))
    }

  }

}
