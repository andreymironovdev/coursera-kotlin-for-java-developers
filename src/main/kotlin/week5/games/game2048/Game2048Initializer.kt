package week5.games.game2048

import week4.board.Cell
import week4.board.GameBoard
import kotlin.random.Random

interface Game2048Initializer<T> {
    /*
     * Specifies the cell and the value that should be added to this cell.
     */
    fun nextValue(board: GameBoard<T?>): Pair<Cell, T>?
}

object RandomGame2048Initializer : Game2048Initializer<Int> {
    private fun generateRandomStartValue(): Int =
        if (Random.nextInt(10) == 9) 4 else 2

    /*
     * Generate a random value and a random cell among free cells
     * that given value should be added to.
     * The value should be 2 for 90% cases, and 4 for the rest of the cases.
     * Use the 'generateRandomStartValue' function above.
     * If the board is full return null.
     */
    override fun nextValue(board: GameBoard<Int?>): Pair<Cell, Int>? = if (board.all { value -> value != null }) {
        null
    } else {
        val randomCell = board.getAllCells().asSequence()
            .filter { cell -> board.get(cell) == null }
            .shuffled()
            .first()
        randomCell to generateRandomStartValue()
    }
}