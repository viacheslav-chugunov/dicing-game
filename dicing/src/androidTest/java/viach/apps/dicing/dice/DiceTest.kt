package viach.apps.dicing.dice

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class DiceTest(private val dice: Dice, private val value: Int) {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun value_valueMustBeEqualWithValueFromConstructor() {
        assertEquals(value, dice.value)
    }

    @Test
    fun iconRes_resourceMustBeValid() {
        context.getString(dice.iconRes)
    }

    @Test
    fun contentDescriptionRes_resourceMustBeValid() {
        context.getString(dice.contentDescriptionRes)
    }
}

class EmptyDiceTest : DiceTest(EmptyDice, 0)
class OneDiceTest : DiceTest(OneDice, 1)
class TwoDiceTest : DiceTest(TwoDice, 2)
class ThreeDiceTest : DiceTest(ThreeDice, 3)
class FourDiceTest : DiceTest(FourDice, 4)
class FiveDiceTest : DiceTest(FiveDice, 5)
class SixDiceTest : DiceTest(SixDice, 6)
