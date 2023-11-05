package week5.playground

class Words {
    private val list = mutableListOf<String>()

    fun String.record() {
        list += this
    }

    operator fun String.unaryPlus() {
        list += this
    }

    override fun toString() = list.toString()
}
