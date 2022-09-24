package viach.apps.dicing.di

import org.koin.dsl.module
import viach.apps.ai.ai.AI
import viach.apps.ai.ai.TwoPlayersEasyAI
import viach.apps.ai.ai.TwoPlayersHardAI
import viach.apps.ai.ai.TwoPlayersNormalAI
import viach.apps.dicing.model.AIDifficulty
import viach.apps.dicing.model.GameType
import viach.apps.dicing.player.FirstPlayer
import viach.apps.dicing.player.SecondPlayer

val aiModule = module {
    factory<AI>(AIDifficulty.EASY.qualifier) {
        TwoPlayersEasyAI(
            game = get(GameType.USER_VS_AI.qualifier),
            ownPlayerPosition =  SecondPlayer().position,
            opponentPlayerPosition =  FirstPlayer().position
        )
    }

    factory<AI>(AIDifficulty.NORMAL.qualifier) {
        TwoPlayersNormalAI(
            game = get(GameType.USER_VS_AI.qualifier),
            ownPlayerPosition =  SecondPlayer().position,
            opponentPlayerPosition =  FirstPlayer().position
        )
    }

    factory<AI>(AIDifficulty.HARD.qualifier) {
        TwoPlayersHardAI(
            game = get(GameType.USER_VS_AI.qualifier),
            ownPlayerPosition =  SecondPlayer().position,
            opponentPlayerPosition =  FirstPlayer().position
        )
    }
}