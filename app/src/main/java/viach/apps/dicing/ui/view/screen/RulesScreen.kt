package viach.apps.dicing.ui.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viach.apps.dicing.R
import viach.apps.dicing.game.Game
import viach.apps.dicing.ui.view.component.GameBar
import viach.apps.dicing.ui.view.component.MaxWidthButton
import viach.apps.dicing.ui.view.component.StatusBar
import viach.apps.dicing.ui.view.component.VerticalSpacer

@Suppress("NAME_SHADOWING")
@Composable
fun RulesScreen(
    game: Game,
    onBackToMenuIntent: () -> Unit
) {
    val scrollState = rememberScrollState()
    var game by rememberSaveable { mutableStateOf(game) }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalSpacer(16.dp)
            Text(
                text = stringResource(R.string.rules_description_part_1),
                textAlign = TextAlign.Justify
            )
            VerticalSpacer(16.dp)
            Text(
                text = stringResource(R.string.playing_field_preview_below),
                textAlign = TextAlign.Justify
            )
            VerticalSpacer(16.dp)
            GameBar(
                gameField = game.getGameField(1),
                rowCellsCount = 3,
                onPlaceDiceIntent = { position ->
                    game = game.makeMove(position, 1).createNextDice()
                    if (game.gameOver) {
                        coroutineScope.launch {
                            delay(2000)
                            game = game.newGame
                        }
                    }
                }
            )
            VerticalSpacer(16.dp)
            Text(
                text = stringResource(R.string.status_bar_preview_below),
                textAlign = TextAlign.Justify
            )
            VerticalSpacer(16.dp)
            StatusBar(
                leftScore = game.getGameField(1).score,
                rightScore = 0,
                dice = game.nextDice!!
            )
            VerticalSpacer(16.dp)
            Text(
                text = stringResource(R.string.rules_description_part_2),
                textAlign = TextAlign.Justify
            )
            VerticalSpacer(16.dp)
            Text(
                text = stringResource(R.string.rules_description_part_3),
                textAlign = TextAlign.Justify
            )
            VerticalSpacer(16.dp)
        }
        MaxWidthButton(
            textRes = R.string.back_to_menu,
            onClick = onBackToMenuIntent,
        )
        VerticalSpacer(16.dp)
    }
}