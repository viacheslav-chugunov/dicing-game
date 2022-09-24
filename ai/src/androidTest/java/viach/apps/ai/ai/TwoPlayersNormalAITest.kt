package viach.apps.ai.ai

import viach.apps.ai.util.TestGame
import viach.apps.dicing.player.FirstPlayer
import viach.apps.dicing.player.SecondPlayer

class TwoPlayersNormalAITest : TwoPlayersAITest(
    TwoPlayersNormalAI(
        game = TestGame(),
        ownPlayerPosition = FirstPlayer().position,
        opponentPlayerPosition = SecondPlayer().position
    )
)