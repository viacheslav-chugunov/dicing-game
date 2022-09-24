package viach.apps.dicing.fieldcell

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmptyFieldCellTest {

    @Test
    fun free_isFree() {
        val field: FieldCell = EmptyFieldCell
        assertTrue(field.free)
    }

    @Test
    fun score_isEqual0() {
        val field: FieldCell = EmptyFieldCell
        assertEquals(0, field.score)
    }

    @Test
    fun dice_diceValueIsEqual0(){
        val field: FieldCell = EmptyFieldCell
        assertEquals(0, field.dice.value)
    }
}