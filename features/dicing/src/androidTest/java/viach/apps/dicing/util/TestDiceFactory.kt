package viach.apps.dicing.util

import kotlinx.parcelize.Parcelize
import viach.apps.dicing.dice.Dice
import viach.apps.dicing.dicefactory.DiceFactory

@Parcelize
class TestDiceFactory(private val dice: Dice) : DiceFactory {
    override fun create(): Dice = dice
}