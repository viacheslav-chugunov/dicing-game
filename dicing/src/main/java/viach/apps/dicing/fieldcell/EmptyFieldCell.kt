package viach.apps.dicing.fieldcell

import kotlinx.parcelize.Parcelize
import viach.apps.dicing.dice.EmptyDice

@Parcelize
internal object EmptyFieldCell : BaseFieldCell(EmptyDice)