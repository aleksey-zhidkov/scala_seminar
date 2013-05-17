import macros.Foo
import Profiler._

def f(x: Int): Int = x + x

println(profile(f, 10))
println(profile(Foo.bar, 10))
println(profile(Foo.bar, 10))

println("Stats:")
Profiler.stat()