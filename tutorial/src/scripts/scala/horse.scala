def possibleSteps(pos: (Int, Int)) =
  for (x <- -2 to 2; y <- -2 to 2; if x != 0 && y != 0 && scala.math.abs(x) != scala.math.abs(y))
  yield (pos._1 + x, pos._2 + y)

def canStep(path: List[(Int, Int)], pos: (Int, Int)) =
  if (path.length == 0) true
  else possibleSteps(path.head).contains(pos)

def nextSteps(available: scala.List[(Int, Int)], path: scala.List[(Int, Int)]): List[(Int, Int)] =
  for (pos <- available; if canStep(path, pos)) yield pos

def getPath(path: List[(Int, Int)], available: List[(Int, Int)]): List[(Int, Int)] =
  if (available.size == 0)
    path
  else
    nextSteps(available, path).foldLeft(List[(Int, Int)]()) (
      (found, e) => found match {
          case Nil => getPath(e :: path, available filter { _ != e })
          case lst: List[(Int, Int)] => lst
        }
    )

def generatePositions(side: Int) = for (x <- Range(0, side); y <- Range(0, side)) yield (x, y)

println(getPath(List(), generatePositions(5).toList))