package viach.apps.dicing.ui.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import viach.apps.dicing.R
import viach.apps.dicing.model.AIDifficulty
import viach.apps.dicing.ui.view.component.AIDifficultyDialog
import viach.apps.dicing.ui.view.component.HorizontalSpacer
import viach.apps.dicing.ui.view.component.MaxWidthButton
import viach.apps.dicing.ui.view.component.VerticalSpacer

@Composable
fun MenuScreen(
    onPlayOpenIntent: (AIDifficulty) -> Unit,
    onTwoPlayersOpenIntent: () -> Unit,
    onRulesOpenIntent: () -> Unit,
) {
    var showAIDifficultyDialog by rememberSaveable { mutableStateOf(false) }

    if (showAIDifficultyDialog) {
        AIDifficultyDialog(
            onDifficultySelected = onPlayOpenIntent,
            onDismissIntent = { showAIDifficultyDialog = false }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.app_icon),
                contentDescription = stringResource(R.string.app_name),
                tint = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.size(48.dp)
            )
            HorizontalSpacer(16.dp)
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.h1
            )
        }
        VerticalSpacer(32.dp)
        MaxWidthButton(
            textRes = R.string.play,
            onClick = { showAIDifficultyDialog = true }
        )
        VerticalSpacer(16.dp)
        MaxWidthButton(
            textRes = R.string.two_players,
            onClick = onTwoPlayersOpenIntent
        )
        VerticalSpacer(16.dp)
        MaxWidthButton(
            textRes = R.string.rules,
            onClick = onRulesOpenIntent
        )
    }
}