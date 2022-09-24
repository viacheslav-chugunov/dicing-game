package viach.apps.dicing.dice

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import viach.apps.dicing.R

@RunWith(AndroidJUnit4::class)
class BaseDiceTest {

    @Test
    fun equals_dicesWithSameContentAreEqual() {
        val dice1: Dice = BaseDice(1, R.drawable.dice_1, R.string.dice_with_value_1)
        val dice2: Dice = BaseDice(1, R.drawable.dice_1, R.string.dice_with_value_1)
        assertEquals(dice1, dice2)
    }

    @Test
    fun equals_diceWithDifferentContentAreNotEqual() {
        val dice1: Dice = BaseDice(1, R.drawable.dice_1, R.string.dice_with_value_1)
        val dice2: Dice = BaseDice(2, R.drawable.dice_2, R.string.dice_with_value_2)
        assertNotEquals(dice1, dice2)
    }

    @Test
    fun hashCode_codeWithSameContentAreEqual() {
        val dice1: Dice = BaseDice(1, R.drawable.dice_1, R.string.dice_with_value_1)
        val dice2: Dice = BaseDice(1, R.drawable.dice_1, R.string.dice_with_value_1)
        assertEquals(dice1.hashCode(), dice2.hashCode())
    }

    @Test
    fun hashCode_codeWithDifferentContentAreNotEqual() {
        val dice1: Dice = BaseDice(1, R.drawable.dice_1, R.string.dice_with_value_1)
        val dice2: Dice = BaseDice(2, R.drawable.dice_2, R.string.dice_with_value_2)
        assertNotEquals(dice1.hashCode(), dice2.hashCode())
    }

}