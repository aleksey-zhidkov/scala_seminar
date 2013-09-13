package object try_keyword {

  def `try`[T <: {def close()}](res: T)(tryBody: T => Unit): CatchOrFinally = new CatchOrFinally(exec(res)(tryBody))

  class CatchOrFinally(f: (CatchBlock) => (FinallyBlock) => Unit) {

    def `;` = {
      f(None)(None)
    }

    def `catch`(catchBody: PartialFunction[Throwable, Unit]) = new Finally(f(Some(catchBody)))

    def `finally`(finallyBody: => Unit) = f(None)(Some({() => finallyBody }))

  }

  class Finally(f: FinallyBlock => Unit) {

    def `;` = {
      f(None)
    }

    def `finally`(finallyBody: => Unit) = {
      f(Some({() => finallyBody}))
    }

  }

  type CatchBlock = Option[PartialFunction[Throwable, Unit]]
  type FinallyBlock = Option[() => Unit]

  private def exec[T <: {def close()}](res: T)(tryBody: T => Unit)(catchBody: CatchBlock)(finallyBody: FinallyBlock) {
    try {
      tryBody(res)
    } catch {
      case t: Throwable => {
        val throwAll: PartialFunction[Throwable, Unit] = {
          case t: Throwable => throw t
        }
        val handler: PartialFunction[Throwable, Unit] = catchBody.getOrElse(throwAll)
        if (handler.isDefinedAt(t)) {
          handler(t)
        } else {
          throw t
        }
      }
    } finally {
      res.close()
      finallyBody.foreach(_())
    }
  }

}
