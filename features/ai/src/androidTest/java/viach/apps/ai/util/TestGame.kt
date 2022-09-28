package viach.apps.ai.util

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import viach.apps.dicing.dice.Dice
import viach.apps.dicing.dicefactory.RandomDiceFactory
import viach.apps.dicing.game.Game
import viach.apps.dicing.game.TwoPlayersGame
import viach.apps.dicing.gamefield.GameField
import viach.apps.dicing.player.FirstPlayer
import viach.apps.dicing.player.SecondPlayer

@Parcelize
class TestGame : Game {
    @IgnoredOnParcel
    var firstPlayerDicesCount: Int = 0

    @IgnoredOnParcel
    var secondPlayerDicesCount: Int = 0

    @IgnoredOnParcel
    private var baseGame: Game = TwoPlayersGame(
        diceFactory = RandomDiceFactory,
        initialFields = listOf(
            TestGameField(FirstPlayer()) { firstPlayerDicesCount = it },
            TestGameField(SecondPlayer()) { secondPlayerDicesCount = it }
        )
    )

    override val nextDice: Dice? get() = baseGame.nextDice

    override val gameOver: Boolean get() = baseGame.gameOver

    override val wonPlayerPosition: Int? get() = baseGame.wonPlayerPosition

    override val newGame: Game get() = baseGame.newGame

    override fun isPlayerMove(playerPosition: Int): Boolean = baseGame.isPlayerMove(playerPosition)

    override fun getGameField(playerPosition: Int): GameField = baseGame.getGameField(playerPosition)

    override fun makeMove(fieldPosition: Int, playerPosition: Int): TestGame {
        baseGame.makeMove(fieldPosition, playerPosition).also { baseGame = it }
        return this
    }

    override fun createNextDice(): Game {
        baseGame = baseGame.createNextDice()
        return this
    }
}