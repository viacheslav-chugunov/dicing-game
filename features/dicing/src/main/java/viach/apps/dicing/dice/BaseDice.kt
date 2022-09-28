package viach.apps.dicing.dice

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
open class BaseDice(
    override val value: Int,
    @DrawableRes override val iconRes: Int,
    @StringRes override val contentDescriptionRes: Int
) : Dice {
    override fun equals(other: Any?): Boolean =
        this === other || other is Dice &&
                value == other.value &&
                iconRes == other.iconRes &&
                contentDescriptionRes == other.contentDescriptionRes

    override fun hashCode(): Int =
        (value * 3 + iconRes * 4 + contentDescriptionRes * 5)
}