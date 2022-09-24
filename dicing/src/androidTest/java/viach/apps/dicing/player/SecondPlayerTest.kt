package viach.apps.dicing.player

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SecondPlayerTest {

    @Test
    fun position_isEqual2() {
        val player: Player = SecondPlayer(10, 1.0)
        Assert.assertEquals(2, player.position)
    }
}