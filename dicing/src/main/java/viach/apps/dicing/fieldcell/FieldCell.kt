package viach.apps.dicing.fieldcell

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import viach.apps.dicing.dice.Dice

interface FieldCell : Parcelable {
    val free: Boolean
    val dice: Dice
    val score: Int get() = dice.value
}