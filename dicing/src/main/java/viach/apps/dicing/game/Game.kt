package viach.apps.dicing.game

import android.os.Parcelable
import viach.apps.dicing.gamefield.GameField
import viach.apps.dicing.dice.Dice

interface Game : Parcelable {
    val nextDice: Dice?
    val gameOver: Boolean
    val wonPlayerPosition: Int?
    val newGame: Game
    fun isPlayerMove(playerPosition: Int): Boolean
    fun getGameField(playerPosition: Int): GameField
    fun makeMove(fieldPosition: Int, playerPosition: Int): Game
    fun createNextDice(): Game
}