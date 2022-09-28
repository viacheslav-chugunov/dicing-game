package viach.apps.ai.ai

import android.os.Parcelable
import viach.apps.dicing.game.Game

interface AI : Parcelable {
    val game: Game
    fun makeMove(): AI
    fun updateGame(game: Game): AI
}