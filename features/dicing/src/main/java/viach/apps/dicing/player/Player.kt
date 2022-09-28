package viach.apps.dicing.player

import android.os.Parcelable

interface Player: Parcelable {
    val position: Int
    val scoreMultiplayer: Double
    val score: Int
    fun updateScore(newScore: Int): Player
    fun updateScoreMultiplayer(newScoreMultiplayer: Double): Player
}