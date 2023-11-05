package week5.playground

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class FibonacciTest {
    @Test
    fun testFirst4Elements() {
        Assert.assertEquals("[0, 1, 1, 2]", fibonacci().take(4).toList().toString())
    }

    @Test
    fun testFirst10Elements() {
        Assert.assertEquals("[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]", fibonacci().take(10).toList().toString())
    }
}
