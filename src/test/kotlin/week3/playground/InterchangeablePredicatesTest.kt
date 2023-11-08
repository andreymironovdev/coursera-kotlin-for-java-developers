package week3.playground

import org.junit.Assert
import org.junit.Test

class InterchangeablePredicatesTest {
    private val list1 = listOf(1, 2, 3)
    private val list2 = listOf(0, 1, 2)

    @Test
    fun should_all_be_nonezero() {
        Assert.assertTrue(list1.allNonZero())
        Assert.assertTrue(list1.allNonZero1())
        Assert.assertTrue(list1.allNonZero2())
    }

    @Test
    fun should_not_all_be_nonzero() {
        Assert.assertFalse(list2.allNonZero())
        Assert.assertFalse(list2.allNonZero1())
        Assert.assertFalse(list2.allNonZero2())
    }

    @Test
    fun should_contain_zero() {
        Assert.assertTrue(list2.containsZero())
        Assert.assertTrue(list2.containsZero1())
        Assert.assertTrue(list2.containsZero2())
    }

    @Test
    fun should_not_contain_zero() {
        Assert.assertFalse(list1.containsZero())
        Assert.assertFalse(list1.containsZero1())
        Assert.assertFalse(list1.containsZero2())
    }
}