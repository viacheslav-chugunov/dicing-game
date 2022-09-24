package viach.apps.dicing.dicefactory

import kotlinx.parcelize.Parcelize
import viach.apps.dicing.dice.*
import kotlin.random.Random

@Parcelize
object RandomDiceFactory : DiceFactory {
    override fun create(): Dice = when ((1..6).random(Random(System.currentTimeMillis()))) {
        1 -> OneDice
        2 -> TwoDice
        3 -> ThreeDice
        4 -> FourDice
        5 -> FiveDice
        else -> SixDice
    }
}