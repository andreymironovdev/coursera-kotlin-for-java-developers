package week2.playground

import org.junit.Assert
import org.junit.Test

class SumExtensionTest {
    @Test
    fun shold_calculate_sum() {
        Assert.assertEquals(listOf(1,2,3).sum(), 6)
    }
}