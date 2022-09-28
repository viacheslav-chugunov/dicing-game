package viach.apps.ai.util

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import viach.apps.dicing.dice.Dice
import viach.apps.dicing.gamefield.GameField
import viach.apps.dicing.gamefield.SquareNineCellsGameField
import viach.apps.dicing.player.Player

@Parcelize
class TestGameField(
    private val player: Player,
    val onDicesCountChanged: (Int) -> Unit
) : GameField {
    @IgnoredOnParcel
    private var dicesCount: Int = 0

    @IgnoredOnParcel
    private var baseGameField: GameField = SquareNineCellsGameField.newInstance(player)

    override val gameOver: Boolean get() = baseGameField.gameOver

    override val score: Int get() = baseGameField.score

    override val position: Int get() = baseGameField.position

    override fun placeDice(dice: Dice, position: Int): GameField =
        baseGameField.placeDice(dice, position).also {
            if (it !== baseGameField) {
                dicesCount++
                onDicesCountChanged(dicesCount)
            }
            baseGameField = it
        }

    override fun pullOutDice(position: Int, removeIf: Dice.() -> Boolean): GameField =
        baseGameField.pullOutDice(position).also {
            if (it !== baseGameField) {
                dicesCount--
                onDicesCountChanged(dicesCount)
            }
            baseGameField = it
        }

    override fun updateScoreMultiplayer(scoreMultiplayer: Double): GameField =
        baseGameField.updateScoreMultiplayer(scoreMultiplayer).also { baseGameField = it }

    override fun getDice(position: Int): Dice = baseGameField.getDice(position)

    override fun isFree(position: Int): Boolean = baseGameField.isFree(position)
}