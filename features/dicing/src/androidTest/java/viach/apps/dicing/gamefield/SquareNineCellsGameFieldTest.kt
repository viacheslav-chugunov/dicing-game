package viach.apps.dicing.gamefield

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import viach.apps.dicing.dice.EmptyDice
import viach.apps.dicing.dice.OneDice
import viach.apps.dicing.dice.TwoDice
import viach.apps.dicing.fieldcell.BaseFieldCell
import viach.apps.dicing.fieldcell.EmptyFieldCell
import viach.apps.dicing.player.FirstPlayer

@RunWith(AndroidJUnit4::class)
class SquareNineCellsGameFieldTest {

    @Test
    fun gameOver_gameOverIfAllCellsIsNotEmpty() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { BaseFieldCell(OneDice) })
        assertTrue(field.gameOver)
    }

    @Test
    fun gameOver_gameOverIfAtLeastOneCellIsEmpty() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { BaseFieldCell(EmptyDice) })
        assertFalse(field.gameOver)
    }

    @Test
    fun position_isEqualWithPositionOfPlayer() {
        val player = FirstPlayer()
        val field: GameField = SquareNineCellsGameField.newInstance(player)
        assertEquals(player.position, field.position)
    }

    @Test
    fun score_withoutBonus() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer())
            .placeDice(OneDice, 1)
            .placeDice(OneDice, 6)
        assertEquals(2, field.score)
    }

    @Test
    fun score_with2CellsHorizontalBonus() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer())
            .placeDice(OneDice, 1)
            .placeDice(OneDice, 2)
        assertEquals(5, field.score)
    }

    @Test
    fun score_with2CellsVerticalBonus() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer())
            .placeDice(OneDice, 1)
            .placeDice(OneDice, 4)
        assertEquals(5, field.score)
    }

    @Test
    fun score_with2CellsDiagonalBonus() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer())
            .placeDice(OneDice, 1)
            .placeDice(OneDice, 5)
        assertEquals(5, field.score)
    }


    @Test
    fun score_with3CellsHorizontalBonus() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer())
            .placeDice(OneDice, 1)
            .placeDice(OneDice, 2)
            .placeDice(OneDice, 3)
        assertEquals(8, field.score)
    }

    @Test
    fun score_with3CellsVerticalBonus() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer())
            .placeDice(OneDice, 1)
            .placeDice(OneDice, 4)
            .placeDice(OneDice, 7)
        assertEquals(8, field.score)
    }

    @Test
    fun score_with3CellsDiagonalBonus() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer())
            .placeDice(OneDice, 1)
            .placeDice(OneDice, 5)
            .placeDice(OneDice, 9)
        assertEquals(8, field.score)
    }

    @Test
    fun placeDice_updatesScore() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer())
            .placeDice(OneDice, 1)
        assertEquals(1, field.score)
    }

    @Test
    fun placeDice_updatesGameOver() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { index ->
            if (index in 0..7) {
                BaseFieldCell(OneDice)
            } else {
                EmptyFieldCell
            }
        }).placeDice(OneDice, 9)
        assertTrue(field.gameOver)
    }

    @Test
    fun pullOutDice_updatesScore() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { index ->
            if (index in 0..7) {
                EmptyFieldCell
            } else {
                BaseFieldCell(OneDice)
            }
        }).pullOutDice(9) { true }
        assertEquals(0, field.score)
    }

    @Test
    fun pullOutDice_updatesGameOver() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { BaseFieldCell(OneDice) })
            .pullOutDice(9) { true }
        assertFalse(field.gameOver)
    }

    @Test
    fun pullOutDice_notUpdatesScore() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { index ->
            if (index in 0..7) {
                EmptyFieldCell
            } else {
                BaseFieldCell(OneDice)
            }
        }).pullOutDice(9) { false }
        assertEquals(1, field.score)
    }

    @Test
    fun pullOutDice_notUpdatesGameOver() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { BaseFieldCell(OneDice) })
            .pullOutDice(9) { false }
        assertTrue(field.gameOver)
    }

    @Test
    fun updateScoreMultiplayer_updatesScore() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { index ->
            if (index in 0..7) {
                EmptyFieldCell
            } else {
                BaseFieldCell(TwoDice)
            }
        }).updateScoreMultiplayer(1.5)
        assertEquals(3, field.score)
    }

    @Test
    fun getDice_returnsDiceByPosition() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { index ->
            if (index in 0..7) {
                EmptyFieldCell
            } else {
                BaseFieldCell(OneDice)
            }
        })
        assertEquals(OneDice.value, field.getDice(9).value)
    }

    @Test
    fun isFree_freeIfDiceIsEmpty() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { index ->
            if (index in 0..7) {
                BaseFieldCell(OneDice)
            } else {
                EmptyFieldCell
            }
        })
        assertTrue(field.isFree(9))
    }

    @Test
    fun isFree_occupyIfDiceIsNotEmpty() {
        val field: GameField = SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { BaseFieldCell(OneDice) })
        assertFalse(field.isFree(9))
    }
}