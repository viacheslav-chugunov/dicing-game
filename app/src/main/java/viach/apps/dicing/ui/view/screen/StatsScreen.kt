package viach.apps.dicing.ui.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import viach.apps.cache.StatsPreferences
import viach.apps.cache.status.StatsCache
import viach.apps.dicing.R
import viach.apps.dicing.ui.view.component.MaxWidthButton
import viach.apps.dicing.ui.view.component.VerticalSpacer

@Composable
fun StatsScreen(
    stats: StatsCache,
    onBackToMenuIntent: () -> Unit
) {
    val scrollState = rememberScrollState()
    val currentStats: StatsPreferences = stats.all.collectAsState(initial = null).value ?: return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        VerticalSpacer(32.dp)
        Text(
            text = stringResource(R.string.wins_losses),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            style = MaterialTheme.typography.body2
        )
        VerticalSpacer(32.dp)
        Row(modifier = Modifier.padding(horizontal = 32.dp)) {
            Text(
                text = stringResource(R.string.easy),
                modifier = Modifier.weight(1f)
            )
            Text(text = "${currentStats.easyModeWinsCount} - ${currentStats.easyModeLossesCount}")
        }
        VerticalSpacer(16.dp)
        Row(modifier = Modifier.padding(horizontal = 32.dp)) {
            Text(
                text = stringResource(R.string.normal),
                modifier = Modifier.weight(1f)
            )
            Text(text = "${currentStats.normalModeWinsCount} - ${currentStats.normalModeLossesCount}")
        }
        VerticalSpacer(16.dp)
        Row(modifier = Modifier.padding(horizontal = 32.dp)) {
            Text(
                text = stringResource(R.string.hard),
                modifier = Modifier.weight(1f)
            )
            Text(text = "${currentStats.hardModeWinsCount} - ${currentStats.hardModeLossesCount}")
        }
        VerticalSpacer(32.dp)
        MaxWidthButton(
            textRes = R.string.reset_stats,
            onClick = stats::clear
        )
        VerticalSpacer(16.dp)
        MaxWidthButton(
            textRes = R.string.back_to_menu,
            onClick = onBackToMenuIntent
        )
        VerticalSpacer(32.dp)
    }
}