package viach.apps.dicing.dicefactory

import android.os.Parcelable
import viach.apps.dicing.dice.Dice

interface DiceFactory : Parcelable {
    fun create() : Dice
}