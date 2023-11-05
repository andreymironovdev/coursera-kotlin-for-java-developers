package week5.playground

import org.junit.Assert
import org.junit.Test

class MemberExtensionsTest {

    @Test
    fun shouldStoreWords() {
        val words = Words()

        with(words) {
            "one".record()
            +"two"
        }

        Assert.assertEquals("[one, two]", words.toString())
    }
}