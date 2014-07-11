import javax.swing.{JLabel, JFrame}

class SquareAndFrame extends JFrame with Function1[Int, Int] {

  def showFrame() {
    setBounds(100, 100, 100, 100)
    setVisible(true)
  }

  def apply(v1: Int): Int = v1 * v1
}

// App - это особый трейт для создания приложений, который в свою очередь расширяет DelayedInit, который
// является интерфесом маркером, при наличаи которого, компилятор оборачивает код инициализации в замыкание
// и вызывает его в методе main
object SquareAndFrame extends App {

  val squareFunc = new SquareAndFrame
  var four = squareFunc(2)

  // а за одно вот вам и интерполяция строк!:) Так же есть интерполятры f и raw и возможность сделать собственный
  val frameObject = new SquareAndFrame
  frameObject.add(new JLabel(s"Four = $four"))
  frameObject.showFrame()

  // Map тоже является функцией:
  val squareMapFunc = Map(2 -> 4)
  four = squareMapFunc(2)

}