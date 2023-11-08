package week3.playground

import org.junit.Assert
import org.junit.Test
import kotlin.test.assertFailsWith

class SafeCastsTest {
    private val s = ""

    @Test
    fun should_be_safe() {
        Assert.assertNull(s as? Int)
    }

    @Test
    fun should_not_be_safe() {
        assertFailsWith<ClassCastException> {
            val ss = s as Int?
        }
    }
}
