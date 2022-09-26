package viach.apps.dicing.ui.view.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viach.apps.ai.ai.AI
import viach.apps.cache.status.StatsCache
import viach.apps.dicing.game.Game
import viach.apps.dicing.R
import viach.apps.dicing.model.AIDifficulty
import viach.apps.dicing.ui.view.component.*

@SuppressLint("SwitchIntDef")
@Suppress("NAME_SHADOWING")
@Composable
fun GameScreen(
    game: Game,
    stats: StatsCache,
    ai: AI? = null,
    difficulty: AIDifficulty? = null,
    onBackToMenuIntent: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val orientation = LocalConfiguration.current.orientation
    var game: Game by rememberSaveable { mutableStateOf(game) }
    var ai: AI? by rememberSaveable { mutableStateOf(ai) }
    var message by rememberSaveable {
        val playerPosition = if (game.isPlayerMove(1)) 1 else 2
        val message = context.getString(R.string.player_format_moves, playerPosition)
        mutableStateOf<String?>(message)
    }
    var scoreNotUpdated by rememberSaveable { mutableStateOf(difficulty != null) }

    message?.let {
        MessageDialog(
            message = it,
            onDismissIntent = { message = null }
        )
    }

    if (game.gameOver) {
        if (scoreNotUpdated) {
            when (difficulty) {
                AIDifficulty.EASY -> stats.addEasyWinLossPoint(game.wonPlayerPosition == 1)
                AIDifficulty.NORMAL -> stats.addNormalWinLossPoint(game.wonPlayerPosition == 1)
                AIDifficulty.HARD -> stats.addHardWinLossPoint(game.wonPlayerPosition == 1)
                null -> {}
            }
            scoreNotUpdated = false
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.player_format_won_the_game, game.wonPlayerPosition!!),
                modifier = Modifier.padding(32.dp),
                style = MaterialTheme.typography.body2
            )
            MaxWidthButton(
                textRes = R.string.play_again,
                onClick = { game = game.newGame }
            )
            VerticalSpacer(16.dp)
            MaxWidthButton(
                textRes = R.string.back_to_menu,
                onClick = onBackToMenuIntent
            )
            VerticalSpacer(16.dp)
        }
    } else {
        when (orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                PortraitScreen(
                    context = context,
                    coroutineScope = coroutineScope,
                    scrollState = scrollState,
                    game = game,
                    ai = ai,
                    onGameChange = { game = it },
                    onAIChange = { ai = it },
                    onMessageChange = { message = it },
                    onBackToMenuIntent = onBackToMenuIntent
                )
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                LandscapeScreen(
                    context = context,
                    coroutineScope = coroutineScope,
                    scrollState = scrollState,
                    game = game,
                    ai = ai,
                    onAIChange = { ai = it },
                    onGameChange = { game = it },
                    onMessageChange = { message = it },
                    onBackToMenuIntent = onBackToMenuIntent
                )
            }
        }
    }
}

@Composable
private fun PortraitScreen(
    context: Context,
    coroutineScope: CoroutineScope,
    scrollState: ScrollState,
    game: Game,
    ai: AI?,
    onGameChange: (Game) -> Unit,
    onAIChange: (AI) -> Unit,
    onMessageChange: (String?) -> Unit,
    onBackToMenuIntent: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GameBar(
            gameField = game.getGameField(1),
            rowCellsCount =  3,
            layoutPadding = PaddingValues(16.dp),
            itemPadding = PaddingValues(8.dp)
        ) { position ->
            if (game.isPlayerMove(1)) {
                val newGame = game.makeMove(position, 1).createNextDice()
                onGameChange(newGame)
                if (ai != null && !newGame.gameOver) {
                    coroutineScope.launch {
                        delay(1000)
                        val newAI = ai.updateGame(newGame).makeMove()
                        onAIChange(newAI)
                        onGameChange(newAI.game.createNextDice())
                    }
                }
            } else {
                onMessageChange(context.getString(R.string.player_format_moves, 2))
            }
        }
        StatusBar(
            leftScore = game.getGameField(1).score,
            rightScore = game.getGameField(2).score,
            dice = game.nextDice
        )
        GameBar(
            gameField = game.getGameField(2),
            rowCellsCount =  3,
            layoutPadding = PaddingValues(16.dp),
            itemPadding = PaddingValues(8.dp),
            itemsClickable = ai == null
        ) { position ->
            if (game.isPlayerMove(2)) {
                onGameChange(game.makeMove(position, 2).createNextDice())
            } else {
                onMessageChange(context.getString(R.string.player_format_moves, 1))
            }
        }
    }
}

@Composable
private fun LandscapeScreen(
    context: Context,
    coroutineScope: CoroutineScope,
    scrollState: ScrollState,
    game: Game,
    ai: AI?,
    onGameChange: (Game) -> Unit,
    onAIChange: (AI) -> Unit,
    onMessageChange: (String?) -> Unit,
    onBackToMenuIntent: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .rotate(90f)
            ) {
                GameBar(
                    gameField = game.getGameField(1),
                    rowCellsCount =  3,
                    layoutPadding = PaddingValues(16.dp),
                    itemPadding = PaddingValues(8.dp),
                    iconRotation = -90f
                ) { position ->
                    if (game.isPlayerMove(1)) {
                        val newGame = game.makeMove(position, 1).createNextDice()
                        onGameChange(newGame)
                        if (ai != null && !newGame.gameOver) {
                            coroutineScope.launch {
                                delay(1000)
                                val newAI = ai.updateGame(newGame).makeMove()
                                onAIChange(newAI)
                                onGameChange(newAI.game.createNextDice())
                            }
                        }
                    } else {
                        onMessageChange(context.getString(R.string.player_format_moves, 2))
                    }
                }
            }
            HorizontalSpacer(16.dp)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .rotate(90f)
            ) {
                GameBar(
                    gameField = game.getGameField(2),
                    rowCellsCount =  3,
                    layoutPadding = PaddingValues(16.dp),
                    itemPadding = PaddingValues(8.dp),
                    iconRotation = -90f,
                    itemsClickable = ai == null
                ) { position ->
                    if (game.isPlayerMove(2)) {
                        onGameChange(game.makeMove(position, 2).createNextDice())
                    } else {
                        onMessageChange(context.getString(R.string.player_format_moves, 1))
                    }
                }
            }
        }
        StatusBar(
            leftScore = game.getGameField(1).score,
            rightScore = game.getGameField(2).score,
            dice = game.nextDice
        )
    }
}