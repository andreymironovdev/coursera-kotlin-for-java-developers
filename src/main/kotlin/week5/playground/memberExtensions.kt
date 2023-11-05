package week5.playground

class Words {
    private val list = mutableListOf<String>()

    internal fun String.record(): Words {
        list.add(this)
        return this@Words
    }

    internal operator fun String.unaryPlus() {
        list.add(this)
    }

    override fun toString() = list.toString()
}
