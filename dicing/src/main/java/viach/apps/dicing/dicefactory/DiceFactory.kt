package viach.apps.dicing.dicefactory

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import viach.apps.dicing.dice.Dice
import kotlin.random.Random

interface DiceFactory : Parcelable {
    fun create() : Dice
}