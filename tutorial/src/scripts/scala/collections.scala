// в скала коллекции делятся по двум направлениям - изменяемые/неизменяем и параллельные/непараллельные
// по умолчанию коллекции не изменяемые

val timeout: Long = 500
var immutableList: List[Long] = List(timeout, timeout, timeout)
// не скомпилируется: list.add(4)
println(immutableList)

// но можно так
// так же здесь представленя особая магия методов начинающихся с ":" - они записываются в обратном порядке
immutableList = timeout + 1 :: immutableList
println(immutableList)

val mutableList = scala.collection.mutable.ListBuffer(1, 2, 3)
mutableList.append(4)

var startTime = System.currentTimeMillis()

immutableList foreach Thread.sleep
// эта штука эквивалента:
// immutableList.foreach({x: Long =>
//   Thread.sleep(x)
// })

val seqTime = System.currentTimeMillis() - startTime
println(s"Seq time = $seqTime")
assert(seqTime > immutableList.size * timeout)


// преобразование списка в паралелленьную последовательность
val parallelSeq = immutableList.par

startTime = System.currentTimeMillis()

parallelSeq foreach Thread.sleep

val parTime = System.currentTimeMillis() - startTime
println(s"Par time = $parTime")
assert(parTime < immutableList.size * timeout)