package week2.playground

fun isValidIdentifier(s: String): Boolean = s.matches(Regex("^[_a-zA-Z][_a-zA-Z0-9]*$"))
