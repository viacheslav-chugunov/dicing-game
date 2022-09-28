package viach.apps.ai.ai

import kotlinx.parcelize.Parcelize
import viach.apps.dicing.game.Game

@Parcelize
class TwoPlayersNormalAI(
    override val game: Game,
    private val ownPlayerPosition: Int,
    private val opponentPlayerPosition: Int
) : AI {

    override fun makeMove(): AI {
        val field = game.getGameField(ownPlayerPosition)
        if (field.gameOver) return this
        val games = mutableListOf<Game>()
        for (i in 1..9) {
            if (field.isFree(i)) {
                games += game.makeMove(i, ownPlayerPosition)
            }
        }
        val maxScoredGame = games.maxBy { it.getGameField(ownPlayerPosition).score }
        return TwoPlayersNormalAI(
            game = maxScoredGame,
            ownPlayerPosition = ownPlayerPosition,
            opponentPlayerPosition = opponentPlayerPosition
        )
    }

    override fun updateGame(game: Game): AI =
        TwoPlayersNormalAI(game, ownPlayerPosition, opponentPlayerPosition)
}