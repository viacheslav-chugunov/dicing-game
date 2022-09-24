package viach.apps.dicing.ui.view.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun MaxWidthButton(
    text: String,
    padding: PaddingValues = PaddingValues(end = 32.dp),
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding),
        onClick = onClick
    ) {
        Text(text = text)
    }
}


@Composable
fun MaxWidthButton(
    @StringRes textRes: Int,
    padding: PaddingValues = PaddingValues(end = 32.dp),
    onClick: () -> Unit
) = MaxWidthButton(
    text = stringResource(textRes),
    padding = padding,
    onClick = onClick
)