package viach.apps.ai.ai

import viach.apps.ai.util.TestGame
import viach.apps.dicing.player.FirstPlayer
import viach.apps.dicing.player.SecondPlayer

class TwoPlayersHardAITest : TwoPlayersAITest(
    TwoPlayersHardAI(
        game = TestGame(),
        ownPlayerPosition = FirstPlayer().position,
        opponentPlayerPosition = SecondPlayer().position
    )
)