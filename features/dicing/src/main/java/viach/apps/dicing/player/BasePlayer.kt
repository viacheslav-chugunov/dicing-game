package viach.apps.dicing.player

import kotlinx.parcelize.Parcelize

@Parcelize
open class BasePlayer(
    private val realScore: Int,
    override val position: Int,
    override val scoreMultiplayer: Double
) : Player {
    override val score: Int get() = (realScore * scoreMultiplayer).toInt()

    override fun updateScore(newScore: Int): Player =
        BasePlayer(newScore, position, scoreMultiplayer)

    override fun updateScoreMultiplayer(newScoreMultiplayer: Double): Player =
        BasePlayer(score, position, newScoreMultiplayer)
}