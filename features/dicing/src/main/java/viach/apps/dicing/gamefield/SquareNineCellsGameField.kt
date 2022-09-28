package viach.apps.dicing.gamefield

import kotlinx.parcelize.Parcelize
import viach.apps.dicing.player.Player
import viach.apps.dicing.dice.Dice
import viach.apps.dicing.fieldcell.BaseFieldCell
import viach.apps.dicing.fieldcell.EmptyFieldCell
import viach.apps.dicing.fieldcell.FieldCell

@Parcelize
class SquareNineCellsGameField private constructor(
    private val player: Player,
    private val cells: List<FieldCell> = List(9) { EmptyFieldCell }
) : GameField {

    override val position: Int get() = player.position

    override val gameOver: Boolean get() = cells.none { it.free }

    override val score: Int get() = player.score

    override fun placeDice(dice: Dice, position: Int): GameField =
        updateCell(BaseFieldCell(dice), position)

    override fun pullOutDice(position: Int, removeIf: Dice.() -> Boolean): GameField =
        if (removeIf(getDice(position))) updateCell(EmptyFieldCell, position) else this

    override fun updateScoreMultiplayer(scoreMultiplayer: Double): GameField =
        SquareNineCellsGameField(player.updateScoreMultiplayer(scoreMultiplayer), cells)

    override fun getDice(position: Int): Dice {
        assertCellsSize()
        assertFieldPosition()
        return cells[position - 1].dice
    }

    override fun isFree(position: Int): Boolean {
        assertCellsSize()
        assertFieldPosition()
        return cells[position - 1].free
    }

    private fun updateCell(cell: FieldCell, position: Int): GameField {
        assertCellsSize()
        assertFieldPosition()
        val newCells = cells.toMutableList().apply { set(position - 1, cell) }
        val newPlayer = player.updateScore(calculateScore(newCells))
        return SquareNineCellsGameField(newPlayer, newCells)
    }

    private fun assertFieldPosition() {
        if (position !in 1..9)
            throw IllegalArgumentException("Received position = $position, but available only from 1 to 9")
    }

    private fun assertCellsSize() {
        if (cells.size != 9)
            throw IllegalStateException("cells.size = ${cells.size}, but it must be equal 9.")
    }

    companion object {
        fun newInstance(
            player: Player,
            cells: List<FieldCell> = List(9) { EmptyFieldCell }
        ): SquareNineCellsGameField {
            val newPlayer = player.updateScore(calculateScore(cells))
            return SquareNineCellsGameField(newPlayer, cells)
        }

        private fun calculateScore(cells: List<FieldCell>): Int {
            val getBonus = { pos1: Int, pos2: Int, pos3: Int ->
                if (cells[pos1 - 1].dice == cells[pos2 - 1].dice && cells[pos1 - 1].dice == cells[pos3 - 1].dice) {
                    cells[pos1 - 1].dice.value * 5
                } else if (cells[pos1 - 1].dice == cells[pos2 - 1].dice) {
                    cells[pos1 - 1].dice.value * 3
                } else if (cells[pos2 - 1].dice == cells[pos3 - 1].dice) {
                    cells[pos2 - 1].dice.value * 3
                } else if (cells[pos1 - 1].dice == cells[pos3 - 1].dice) {
                    cells[pos1 - 1].dice.value * 3
                } else {
                    0
                }
            }
            val bonus = getBonus(1, 2, 3) +
                    getBonus(4, 5, 6) +
                    getBonus(7, 8, 9) +
                    getBonus(1, 4, 7) +
                    getBonus(2, 5, 8) +
                    getBonus(3, 6, 9) +
                    getBonus(1, 5, 9) +
                    getBonus(3, 5, 7)
            return cells.sumOf { it.score } + bonus
        }
    }
}