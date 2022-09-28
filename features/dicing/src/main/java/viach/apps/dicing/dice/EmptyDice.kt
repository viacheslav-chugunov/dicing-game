package viach.apps.dicing.dice

import kotlinx.parcelize.Parcelize
import viach.apps.dicing.R

@Parcelize
object EmptyDice : BaseDice(0, R.drawable.ic_cross, R.string.empty_cell)