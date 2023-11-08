package week3.playground

import org.junit.Assert
import org.junit.Test

class IsEmptyOrNullTest {
    @Test
    fun should_be_empty_or_null() {
        val s1: String? = null
        Assert.assertTrue(s1.isEmptyOrNull())
        val s2 = ""
        Assert.assertTrue(s2.isEmptyOrNull())
    }

    @Test
    fun should_not_be_empty_or_null() {
        val s = "   "
        Assert.assertFalse(s.isEmptyOrNull())
    }
}