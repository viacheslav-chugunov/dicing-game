package viach.apps.dicing.player

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BasePlayerTest {

    @Test
    fun score_scoreWithoutMultiplayerEqualRealScore() {
        val player: Player = BasePlayer(10, 1, 1.0)
        assertEquals(10, player.score)
    }

    @Test
    fun score_scoreWithMultiplayerEqualRealScoreMultipliedByMultiplayer() {
        val player: Player = BasePlayer(10, 1, 1.5)
        assertEquals(15, player.score)
    }

    @Test
    fun updateScore_updatesOnlyRealScore() {
        val player: Player = BasePlayer(10, 1, 1.5)
            .updateScore(20)
        assertEquals(30, player.score)
    }

    @Test
    fun updateScoreMultiplayer_updatesOnlyScoreMultiplayer() {
        val player: Player = BasePlayer(10, 1, 1.5)
            .updateScoreMultiplayer(2.0)
        assertEquals(2.0, player.scoreMultiplayer, 0.00001)
    }
}