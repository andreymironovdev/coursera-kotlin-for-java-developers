package week4.playground

fun fibonacci(): Sequence<Int> = sequence {
    var prev = 0
    var current = 1

    yield(prev)
    yield(current)

    while (true) {
        val next = prev + current
        prev = current
        current = next
        yield(next)

    }
}
