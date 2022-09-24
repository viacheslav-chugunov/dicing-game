package viach.apps.dicing.ui.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import viach.apps.dicing.dice.Dice

@Composable
fun StatusBar(
    leftScore: Int,
    rightScore: Int,
    dice: Dice?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = leftScore.toString(),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Box(modifier = Modifier.size(100.dp)) {
            if (dice != null) {
                Icon(
                    painter = painterResource(dice.iconRes),
                    contentDescription = stringResource(dice.contentDescriptionRes),
                    modifier = Modifier.matchParentSize(),
                    tint = MaterialTheme.colors.primaryVariant
                )
            }
        }
        Text(
            text = rightScore.toString(),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}