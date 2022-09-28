package viach.apps.dicing.dicefactory

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RandomDiceFactoryTest {

    @Test
    fun create_createsDiceInRangeFromOneToSix() {
        val factory: DiceFactory = RandomDiceFactory
        for (i in 0..10_000) {
            assertTrue(factory.create().value in 1..6)
        }
    }
}