package week5.games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    var inversionsCount = 0

    permutation
        .withIndex()
        .forEach { (idx1, value1) ->
            val currentInversionsCount = permutation
                .drop(idx1 + 1)
                .count { value2 -> value1 > value2 }
            inversionsCount += currentInversionsCount
        }

    return inversionsCount % 2 == 0
}