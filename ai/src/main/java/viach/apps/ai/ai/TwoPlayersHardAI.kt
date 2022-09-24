package viach.apps.ai.ai

import kotlinx.parcelize.Parcelize
import viach.apps.dicing.game.Game

@Parcelize
class TwoPlayersHardAI(
    override val game: Game,
    private val ownPlayerPosition: Int,
    private val opponentPlayerPosition: Int
): AI {

    override fun makeMove(): AI {
        val field = game.getGameField(ownPlayerPosition)
        if (field.gameOver) return this
        val games = mutableListOf<Game>()
        for (i in 1..9) {
            if (field.isFree(i)) {
                games += game.makeMove(i, ownPlayerPosition)
            }
        }
        val maxScore = games.maxOf { it.getGameField(ownPlayerPosition).score }
        val maxScoredGames = games.filter {
            maxScore == it.getGameField(ownPlayerPosition).score
        }
        val minOpponentScoredGame = maxScoredGames.minBy { it.getGameField(opponentPlayerPosition).score }
        return TwoPlayersNormalAI(
            game = minOpponentScoredGame,
            ownPlayerPosition = ownPlayerPosition,
            opponentPlayerPosition = opponentPlayerPosition
        )
    }

    override fun updateGame(game: Game): AI =
        TwoPlayersHardAI(game, ownPlayerPosition, opponentPlayerPosition)
}