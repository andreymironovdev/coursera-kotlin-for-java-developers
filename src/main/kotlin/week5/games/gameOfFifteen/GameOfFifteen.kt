package week5.games.gameOfFifteen

import week4.board.Direction
import week5.games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game = GameOfFifteen(initializer)

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {
    private val permutation: MutableList<Int?> = mutableListOf()

    override fun initialize() {
        permutation.clear()
        permutation.addAll(initializer.initialPermutation)
        permutation.add(null)
    }

    override fun canMove(): Boolean {
        return true
    }

    override fun hasWon(): Boolean {
        return permutation == (1..15).toList() + listOf(null)
    }

    override fun processMove(direction: Direction) {
        val indexOfNull = permutation.indexOf(null)
        val indexOfElementToMove: Int? = when (direction) {
            Direction.UP -> if (indexOfNull in 12..15) null else indexOfNull + 4
            Direction.DOWN -> if (indexOfNull in 0..3) null else indexOfNull - 4
            Direction.LEFT -> if (indexOfNull in 3..15 step 4) null else indexOfNull + 1
            Direction.RIGHT -> if (indexOfNull in 0..12 step 4) null else indexOfNull - 1
        }

        indexOfElementToMove?.run {
            permutation[indexOfNull] = permutation[this]
            permutation[this] = null
        }
    }

    override fun get(i: Int, j: Int): Int? {
        if (i !in 1..4 || j !in 1..4) {
            return null
        }

        return permutation[(i - 1) * 4 + (j - 1)]
    }

}