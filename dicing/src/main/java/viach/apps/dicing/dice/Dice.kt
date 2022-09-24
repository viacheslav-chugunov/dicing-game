package viach.apps.dicing.dice

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import viach.apps.dicing.R

interface Dice : Parcelable {
    val value: Int
    @get:DrawableRes
    val iconRes: Int
    @get:StringRes
    val contentDescriptionRes: Int
}