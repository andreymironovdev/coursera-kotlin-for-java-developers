package week3.nicestring

fun String.isNice(): Boolean =
    listOf(hasNotBadSubs(), hasAtLeast3Vowels(), containsDoubleLetter()).sumBy { if (it) 1 else 0 } > 1

fun String.hasNotBadSubs(): Boolean = listOf("ba", "be", "bu").all { !contains(it) }
fun String.hasAtLeast3Vowels(): Boolean = sumBy { if (listOf('a', 'e', 'i', 'o', 'u').contains(it)) 1 else 0 } > 2
fun String.containsDoubleLetter(): Boolean = withIndex().any { (index, value) ->
    index < length - 1 && value == get(index + 1)
}