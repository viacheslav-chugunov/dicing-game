package viach.apps.dicing.fieldcell

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import viach.apps.dicing.dice.Dice

@Parcelize
internal open class BaseFieldCell(final override val dice: Dice) : FieldCell {
    @IgnoredOnParcel
    override val free: Boolean = dice.value <= 0
}