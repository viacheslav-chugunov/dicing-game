package viach.apps.dicing.ui.view.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import viach.apps.dicing.R

@Composable
fun MessageDialog(
    message: String,
    title: String = stringResource(R.string.warning),
    onDismissIntent: () -> Unit
) {
    Dialog(onDismissRequest = onDismissIntent) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            Box(modifier = Modifier.background(MaterialTheme.colors.primary),) {
                Text(
                    text = title,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body2
                )
            }
            VerticalSpacer(16.dp)
            Text(
                text = message,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            VerticalSpacer(16.dp)
            MaxWidthButton(
                textRes = R.string.okay,
                onClick = onDismissIntent
            )
            VerticalSpacer(16.dp)
        }
    }
}


@Composable
fun MessageDialog(
    @StringRes messageRes: Int,
    onDismissIntent: () -> Unit
) = MessageDialog(
    message = stringResource(messageRes),
    onDismissIntent = onDismissIntent
)