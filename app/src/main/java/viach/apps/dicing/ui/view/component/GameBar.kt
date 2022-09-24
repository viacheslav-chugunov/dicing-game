package viach.apps.dicing.ui.view.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onSizeChanged
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
    var width by remember { mutableStateOf(0.dp) }
    val itemSize = width / (rowCellsCount * rowCellsCount)

    Column(
        modifier = Modifier
            .padding(layoutPadding)
            .fillMaxWidth()
            .onSizeChanged { width = it.width.dp * 0.98f },
    ) {
        for (y in 0 until rowCellsCount) {
            Row(modifier = Modifier.height(itemSize)) {
                for (x in 0 until rowCellsCount) {
                    val position = y * 3 + x + 1
                    val dice = gameField.getDice(position)
                    Row(
                        modifier = Modifier.clickable(enabled = itemsClickable && gameField.isFree(position)) {
                            onPlaceDiceIntent(position)
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(dice.iconRes),
                            contentDescription = stringResource(dice.contentDescriptionRes),
                            modifier = Modifier
                                .size(itemSize)
                                .padding(itemPadding)
                                .rotate(iconRotation),
                            tint = MaterialTheme.colors.primaryVariant
                        )
                    }
                }
            }
        }
    }
}