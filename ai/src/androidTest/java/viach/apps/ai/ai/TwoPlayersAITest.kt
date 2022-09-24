package viach.apps.ai.ai

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import viach.apps.ai.util.TestGame
import viach.apps.dicing.dicefactory.RandomDiceFactory
import viach.apps.dicing.game.TwoPlayersGame

@RunWith(AndroidJUnit4::class)
abstract class TwoPlayersAITest(private val ai: AI) {

    init {
        assert(ai.game.isPlayerMove(1))
        assert(ai.game is TestGame)
    }

    @Test
    fun game_receivesActualGame() {
        assertTrue(ai.game.isPlayerMove(1))
    }

    @Test
    fun updateGame_updatesActualGame() {
        val newGame = TwoPlayersGame(RandomDiceFactory, playerPositionMove = 2)
        assertTrue(ai.updateGame(newGame).game.isPlayerMove(2))
    }

    @Test
    fun makeMove_setsNextDiceIntoFreeCell() {
        val game = ai.makeMove().game as TestGame
        assertEquals(1, game.firstPlayerDicesCount)
    }
}