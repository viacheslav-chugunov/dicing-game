package viach.apps.dicing.ui.view.component

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
import viach.apps.dicing.model.AIDifficulty

@Composable
fun AIDifficultyDialog(
    onDifficultySelected: (AIDifficulty) -> Unit,
    onDismissIntent: () -> Unit
) {
    Dialog(onDismissRequest = onDismissIntent) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            Box(modifier = Modifier.background(MaterialTheme.colors.primary),) {
                Text(
                    text = stringResource(R.string.select_difficulty),
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body2
                )
            }
            VerticalSpacer(16.dp)
            MaxWidthButton(
                textRes = R.string.easy,
                onClick = {
                    onDifficultySelected(AIDifficulty.EASY)
                    onDismissIntent()
                }
            )
            VerticalSpacer(16.dp)
            MaxWidthButton(
                textRes = R.string.normal,
                onClick = {
                    onDifficultySelected(AIDifficulty.EASY)
                    onDismissIntent()
                }
            )
            VerticalSpacer(16.dp)
            MaxWidthButton(
                textRes = R.string.hard,
                onClick = {
                    onDifficultySelected(AIDifficulty.EASY)
                    onDismissIntent()
                }
            )
            VerticalSpacer(16.dp)
        }
    }
}