package viach.apps.dicing.gamefield

import android.os.Parcelable
import viach.apps.dicing.dice.Dice

interface GameField : Parcelable {
    val gameOver: Boolean
    val score: Int
    val position: Int
    fun placeDice(dice: Dice, position: Int): GameField
    fun pullOutDice(position: Int, removeIf: Dice.() -> Boolean = { true }): GameField
    fun updateScoreMultiplayer(scoreMultiplayer: Double): GameField
    fun getDice(position: Int): Dice
    fun isFree(position: Int): Boolean
}