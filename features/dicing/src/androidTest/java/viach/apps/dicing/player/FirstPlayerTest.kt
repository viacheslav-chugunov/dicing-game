package viach.apps.dicing.player

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirstPlayerTest {

    @Test
    fun position_isEqual1() {
        val player: Player = FirstPlayer(10, 1.0)
        assertEquals(1, player.position)
    }
}