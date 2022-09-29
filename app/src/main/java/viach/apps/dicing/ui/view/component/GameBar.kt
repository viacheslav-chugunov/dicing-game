package viach.apps.dicing.ui.view.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import viach.apps.dicing.gamefield.GameField

@Composable
fun GameBar(
    gameField: GameField,
    rowCellsCount: Int,
    layoutPadding: PaddingValues = PaddingValues(12.dp),
    itemPadding: PaddingValues = PaddingValues(8.dp),
    iconRotation: Float = 0f,
    itemsClickable: Boolean = true,
    onPlaceDiceIntent: (position: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(layoutPadding)
    ) {
        for (y in 0 until rowCellsCount) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (x in 0 until rowCellsCount) {
                    val position = y * 3 + x + 1
                    val dice = gameField.getDice(position)
                    val clickable = itemsClickable && gameField.isFree(position)
                    Icon(
                        painter = painterResource(dice.iconRes),
                        contentDescription = stringResource(dice.contentDescriptionRes),
                        modifier = Modifier
                            .clickable(clickable) { onPlaceDiceIntent(position) }
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(itemPadding)
                            .rotate(iconRotation),
                        tint = MaterialTheme.colors.primaryVariant
                    )
                }
            }
        }
    }
}