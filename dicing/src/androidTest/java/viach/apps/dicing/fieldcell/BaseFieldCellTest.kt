package viach.apps.dicing.fieldcell

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import viach.apps.dicing.dice.EmptyDice
import viach.apps.dicing.dice.OneDice

@RunWith(AndroidJUnit4::class)
class BaseFieldCellTest {

    @Test
    fun score_isEqualValueFromDice() {
        val field: FieldCell = BaseFieldCell(OneDice)
        assertEquals(OneDice.value, field.score)
    }

    @Test
    fun free_occupyIfDiceValueIs1OrMore() {
        val field: FieldCell = BaseFieldCell(OneDice)
        assertFalse(field.free)
    }

    @Test
    fun free_freeIfDiceValueIsEquals0() {
        val field: FieldCell = BaseFieldCell(EmptyDice)
        assertTrue(field.free)
    }
}