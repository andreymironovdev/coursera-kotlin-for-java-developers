package week4.board

open class SquareBoardImpl(override val width: Int) : SquareBoard {
    protected val cells: List<List<Cell>> = (1..width)
        .map { x -> (1..width).map { y -> Cell(x, y) } }

    override fun getCellOrNull(i: Int, j: Int): Cell? = cells.getOrNull(i - 1)?.getOrNull(j - 1)

    override fun getCell(i: Int, j: Int): Cell = cells[i - 1][j - 1]

    override fun getAllCells(): Collection<Cell> = cells.flatten()

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> = jRange
        .filter { col -> col in 1..width }
        .map { col -> cells.get(i - 1).get(col - 1) }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> = iRange
        .filter { row -> row in 1..width }
        .map { row -> cells.get(row - 1).get(j - 1) }

    override fun Cell.getNeighbour(direction: Direction): Cell? = when (direction) {
        Direction.UP -> cells.getOrNull(this.i - 2)?.getOrNull(this.j - 1)
        Direction.DOWN -> cells.getOrNull(this.i)?.getOrNull(this.j - 1)
        Direction.LEFT -> cells.getOrNull(this.i - 1)?.getOrNull(this.j - 2)
        Direction.RIGHT -> cells.getOrNull(this.i - 1)?.getOrNull(this.j)
    }

}

class GameBoardImpl<T>(width: Int) : SquareBoardImpl(width), GameBoard<T> {
    private val values: MutableMap<Cell, T?> = cells.flatten()
        .associateBy(keySelector = { it }, valueTransform = { null })
        .toMutableMap()

    override fun get(cell: Cell): T? = values.getOrDefault(cell, null)

    override fun all(predicate: (T?) -> Boolean): Boolean = values.values.all(predicate)

    override fun any(predicate: (T?) -> Boolean): Boolean = values.values.any(predicate)

    override fun find(predicate: (T?) -> Boolean): Cell? = values.entries
        .find { e -> predicate.invoke(e.value) }
        ?.key

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> = values.entries
        .filter { e -> predicate.invoke(e.value) }
        .map { e -> e.key }

    override fun set(cell: Cell, value: T?) {
        values.put(cell, value)
    }
}


fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)

fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(width)

