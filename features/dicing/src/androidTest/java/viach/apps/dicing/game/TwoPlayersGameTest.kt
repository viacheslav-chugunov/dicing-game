package viach.apps.dicing.game

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import viach.apps.dicing.dice.OneDice
import viach.apps.dicing.dice.TwoDice
import viach.apps.dicing.dicefactory.RandomDiceFactory
import viach.apps.dicing.fieldcell.BaseFieldCell
import viach.apps.dicing.fieldcell.EmptyFieldCell
import viach.apps.dicing.gamefield.SquareNineCellsGameField
import viach.apps.dicing.player.FirstPlayer
import viach.apps.dicing.player.SecondPlayer
import viach.apps.dicing.util.TestDiceFactory

@RunWith(AndroidJUnit4::class)
class TwoPlayersGameTest {

    @Test
    fun nextDice_exists() {
        val game: Game = TwoPlayersGame(RandomDiceFactory, RandomDiceFactory.create())
        assertNotNull(game.nextDice)
    }

    @Test
    fun nextDice_notExists() {
        val game: Game = TwoPlayersGame(RandomDiceFactory, null)
        assertNull(game.nextDice)
    }

    @Test
    fun gameOver_ifFirstFieldHasNoEmptyCells() {
        val fields = listOf(
            SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { BaseFieldCell(OneDice) }),
            SquareNineCellsGameField.newInstance(SecondPlayer(), List(9) { EmptyFieldCell })
        )
        val game: Game = TwoPlayersGame(RandomDiceFactory, initialFields = fields)
        assertTrue(game.gameOver)
    }

    @Test
    fun gameOver_ifSecondFieldHasNoEmptyCells() {
        val fields = listOf(
            SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { EmptyFieldCell }),
            SquareNineCellsGameField.newInstance(SecondPlayer(), List(9) { BaseFieldCell(OneDice) })
        )
        val game: Game = TwoPlayersGame(RandomDiceFactory, initialFields = fields)
        assertTrue(game.gameOver)
    }

    @Test
    fun gameOver_gameNotOverIfTwoFieldsHasEmptyCells() {
        val game: Game = TwoPlayersGame(RandomDiceFactory)
        assertFalse(game.gameOver)
    }

    @Test
    fun wonPlayerPosition_ifGameIsNotOverReturnsNull() {
        val game: Game = TwoPlayersGame(RandomDiceFactory)
        assertNull(game.wonPlayerPosition)
    }

    @Test
    fun wonPlayerPosition_firstPlayerPositionIfIsWinner() {
        val fields = listOf(
            SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { BaseFieldCell(TwoDice) }),
            SquareNineCellsGameField.newInstance(SecondPlayer(), List(9) { BaseFieldCell(OneDice) })
        )
        val game: Game = TwoPlayersGame(RandomDiceFactory, initialFields = fields)
        assertEquals(FirstPlayer().position, game.wonPlayerPosition)
    }

    @Test
    fun wonPlayerPosition_secondPlayerPositionIfIsWinner() {
        val fields = listOf(
            SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { BaseFieldCell(OneDice) }),
            SquareNineCellsGameField.newInstance(SecondPlayer(), List(9) { BaseFieldCell(TwoDice) })
        )
        val game: Game = TwoPlayersGame(RandomDiceFactory, initialFields = fields)
        assertEquals(SecondPlayer().position, game.wonPlayerPosition)
    }

    @Test
    fun newGame_returnsNextDiceFromInitial() {
        val game: Game = TwoPlayersGame(RandomDiceFactory, null)
            .createNextDice()
            .newGame
        assertNull(game.nextDice)
    }

    @Test
    fun newGame_returnsGameFieldsFromInitial() {
        val fields = listOf(
            SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { index ->
                if (index in 0..7) {
                    EmptyFieldCell
                } else {
                    BaseFieldCell(OneDice)
                }
            }),
            SquareNineCellsGameField.newInstance(SecondPlayer(), List(9) { EmptyFieldCell })
        )
        val game: Game = TwoPlayersGame(TestDiceFactory(TwoDice), initialFields = fields)
            .makeMove(9, 1)
            .newGame
        assertEquals(OneDice, game.getGameField(1).getDice(9))
    }

    @Test
    fun isPlayerMove_trueIfFirstPlayerMoves() {
        val game: Game = TwoPlayersGame(RandomDiceFactory, playerPositionMove = 2)
            .makeMove(5, 2)
        assertTrue(game.isPlayerMove(1))
    }

    @Test
    fun getGameField_returnsGameFieldOfFirstPlayer() {
        val fields = listOf(
            SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { index ->
                if (index in 0..7) {
                    EmptyFieldCell
                } else {
                    BaseFieldCell(OneDice)
                }
            }),
            SquareNineCellsGameField.newInstance(SecondPlayer(), List(9) { EmptyFieldCell })
        )
        val game: Game = TwoPlayersGame(RandomDiceFactory, initialFields = fields)
        assertEquals(OneDice, game.getGameField(1).getDice(9))
    }

    @Test
    fun getGameField_returnsGameFieldOfSecondPlayer() {
        val fields = listOf(
            SquareNineCellsGameField.newInstance(FirstPlayer(), List(9) { EmptyFieldCell }),
            SquareNineCellsGameField.newInstance(SecondPlayer(), List(9) { index ->
                if (index in 0..7) {
                    EmptyFieldCell
                } else {
                    BaseFieldCell(OneDice)
                }
            })
        )
        val game: Game = TwoPlayersGame(RandomDiceFactory, initialFields = fields)
        assertEquals(OneDice, game.getGameField(2).getDice(9))
    }

    @Test
    fun createNextDice_generatesNextDice() {
        val game: Game = TwoPlayersGame(RandomDiceFactory, initialNextDice = null)
            .createNextDice()
        assertNotNull(game.nextDice)
    }

    @Test
    fun makeMove_setsDiceIntoPosition() {
        val game: Game = TwoPlayersGame(TestDiceFactory(OneDice))
            .makeMove(1, 1)
        assertEquals(1, game.getGameField(1).score)
    }

    @Test
    fun makeMove_calculatesBonusOf2Cells() {
        val game: Game = TwoPlayersGame(TestDiceFactory(OneDice))
            .makeMove(1, 1)
            .makeMove(2, 1)
        assertEquals(5, game.getGameField(1).score)
    }

    @Test
    fun makeMove_calculatesBonusOf3Cells() {
        val game: Game = TwoPlayersGame(TestDiceFactory(OneDice))
            .makeMove(1, 1)
            .makeMove(2, 1)
            .makeMove(3, 1)
        assertEquals(8, game.getGameField(1).score)
    }

    @Test
    fun makeMove_removesOpponentDicesInSameLine() {
        val game: Game = TwoPlayersGame(TestDiceFactory(OneDice))
            .makeMove(1, 1)
            .makeMove(4, 1)
            .makeMove(1, 2)
        assertEquals(0, game.getGameField(1).score)
    }
}