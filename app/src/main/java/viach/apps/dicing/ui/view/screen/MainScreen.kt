package viach.apps.dicing.ui.view.screen

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.get
import viach.apps.ai.ai.AI
import viach.apps.dicing.game.Game
import viach.apps.dicing.model.AIDifficulty
import viach.apps.dicing.model.GameType
import viach.apps.dicing.model.Screen

@Composable
fun MainScreen(
    userVsUserGameFactory: @Composable () -> Game = { get(GameType.USER_VS_USER.qualifier) },
    userVsAiGame: Game = get(GameType.USER_VS_AI.qualifier),
    easyAI: AI = get(AIDifficulty.EASY.qualifier),
    normalAI: AI = get(AIDifficulty.NORMAL.qualifier),
    hardAI: AI = get(AIDifficulty.HARD.qualifier)
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Menu.route) {
        composable(Screen.Menu.route) {
            MenuScreen(
                onPlayOpenIntent = { difficulty ->
                    when (difficulty) {
                        AIDifficulty.EASY -> navController.navigate(Screen.PlayEasyGame.route)
                        AIDifficulty.NORMAL -> navController.navigate(Screen.PlayNormalGame.route)
                        AIDifficulty.HARD -> navController.navigate(Screen.PlayHardGame.route)
                    }
                },
                onTwoPlayersOpenIntent = { navController.navigate(Screen.TwoPlayersGame.route) },
                onRulesOpenIntent = { navController.navigate(Screen.Rules.route) }
            )
        }
        composable(Screen.PlayEasyGame.route) {
            GameScreen(
                game = userVsAiGame,
                ai = easyAI,
                onBackToMenuIntent = { navController.popBackStack() }
            )
        }
        composable(Screen.PlayNormalGame.route) {
            GameScreen(
                game = userVsAiGame,
                ai = normalAI,
                onBackToMenuIntent = { navController.popBackStack() }
            )
        }
        composable(Screen.PlayHardGame.route) {
            GameScreen(
                game = userVsAiGame,
                ai = hardAI,
                onBackToMenuIntent = { navController.popBackStack() }
            )
        }
        composable(Screen.TwoPlayersGame.route) {
            GameScreen(
                game = userVsUserGameFactory(),
                onBackToMenuIntent = { navController.popBackStack() }
            )
        }
        composable(Screen.Rules.route) {
            RulesScreen(
                game = userVsUserGameFactory(),
                onBackToMenuIntent = { navController.popBackStack() }
            )
        }
    }

}