import scala.actors.{Futures, Future}

val future: Future[String] = Futures.future {
  Thread.sleep(1500)
  "Done"
}

println("Мне не интересен результат выполнения фьючера")

println("А мне - интересен. Результат = " + future())


// а теперь давайте посчитаем ответ на главный вопрос жизни, вселенной и всего такого

println("Вычислем ответ на главный вопрос жизни, вселенной и всего такого")
val startTime = System.currentTimeMillis()
val args = List(20, 15, 5, 2)

val futures = args.map( {arg => Futures.future({
  Thread.sleep(500)
  arg
})})

val subAnswers = futures.map(_())

println("Ответ на главный вопрос жизни, вселенной и всего такого = " + subAnswers.reduce(_ + _) )
println("Время вычисления = " + (System.currentTimeMillis() - startTime))