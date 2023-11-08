package week2.playground

import org.junit.Assert
import org.junit.Test

class CheckingIdentifierTest {
    @Test
    fun shold_be_valid() {
        Assert.assertTrue(isValidIdentifier("name"))
        Assert.assertTrue(isValidIdentifier("_name"))
        Assert.assertTrue(isValidIdentifier("_12"))
    }

    @Test
    fun shold_be_invalid() {
        Assert.assertFalse(isValidIdentifier(""))
        Assert.assertFalse(isValidIdentifier("012"))
        Assert.assertFalse(isValidIdentifier("no$"))
    }
}