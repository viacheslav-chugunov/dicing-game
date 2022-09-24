package viach.apps.dicing.player

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface Player: Parcelable {
    val position: Int
    val scoreMultiplayer: Double
    val score: Int
    fun updateScore(newScore: Int): Player
    fun updateScoreMultiplayer(newScoreMultiplayer: Double): Player
}