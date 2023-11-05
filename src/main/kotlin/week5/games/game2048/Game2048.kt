package week5.games.game2048

import week4.board.Cell
import week4.board.Direction
import week4.board.GameBoard
import week4.board.createGameBoard
import week5.games.game.Game

/*
 * Your task is to implement the game 2048 https://en.wikipedia.org/wiki/2048_(video_game).
 * Implement the utility methods below.
 *
 * After implementing it you can try to play the game running 'PlayGame2048'.
 */
fun newGame2048(initializer: Game2048Initializer<Int> = RandomGame2048Initializer): Game =
    Game2048(initializer)

class Game2048(private val initializer: Game2048Initializer<Int>) : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        repeat(2) {
            board.addNewValue(initializer)
        }
    }

    override fun canMove() = board.any { it == null }

    override fun hasWon() = board.any { it == 2048 }

    override fun processMove(direction: Direction) {
        if (board.moveValues(direction)) {
            board.addNewValue(initializer)
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }
}

/*
 * Add a new value produced by 'initializer' to a specified cell in a board.
 */
fun GameBoard<Int?>.addNewValue(initializer: Game2048Initializer<Int>) {
    val nextValue = initializer.nextValue(this)
    nextValue?.run {
        this@addNewValue.set(this.first, this.second)
    }
}

/*
 * Update the values stored in a board,
 * so that the values were "moved" in a specified rowOrColumn only.
 * Use the helper function 'moveAndMergeEqual' (in Game2048Helper.kt).
 * The values should be moved to the beginning of the row (or column),
 * in the same manner as in the function 'moveAndMergeEqual'.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValuesInRowOrColumn(rowOrColumn: List<Cell>): Boolean {
    val nonNullsCount = rowOrColumn
        .asSequence()
        .mapNotNull { cell -> this[cell] }
        .count()

    if (nonNullsCount == 0) {
        return false
    }

    val list = rowOrColumn
        .map { this.get(it) }
        .moveAndMergeEqual { it * 2 }

    if (nonNullsCount == rowOrColumn.size && list.size == rowOrColumn.size) {
        return false
    }

    for ((idx, cell) in rowOrColumn.withIndex()) {
        this[cell] = list.getOrNull(idx)
    }

    return true
}

/*
 * Update the values stored in a board,
 * so that the values were "moved" to the specified direction
 * following the rules of the 2048 game .
 * Use the 'moveValuesInRowOrColumn' function above.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValues(direction: Direction): Boolean = (1..this.width)
    .map { idx ->
        when (direction) {
            Direction.UP -> this.getColumn(1..this.width, idx)
            Direction.DOWN -> this.getColumn(this.width downTo 1, idx)
            Direction.LEFT -> this.getRow(idx, 1..this.width)
            Direction.RIGHT -> this.getRow(idx, this.width downTo 1)
        }
    }
    .map(this::moveValuesInRowOrColumn)
    .any { it }
