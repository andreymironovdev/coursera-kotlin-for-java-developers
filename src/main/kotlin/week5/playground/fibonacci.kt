package week5.playground

fun fibonacci(): Sequence<Int> = sequence {
    var elements = Pair(0, 1)

    while (true) {
        yield(elements.first)
        elements = elements.second to elements.first + elements.second
    }
}
