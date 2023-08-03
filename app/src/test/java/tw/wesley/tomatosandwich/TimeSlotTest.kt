package tw.wesley.tomatosandwich

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import tw.wesley.tomatosandwich.model.TimeSlot

class TimeSlotTest {

    @Test
    fun testIsOccupiedBy() {
        // Starts from 1500
        assertFalse(TimeSlot(1500).isOccupiedBy(TimeSlot(1400))) // 60 mins before is not occupied
        assertTrue(TimeSlot(1500).isOccupiedBy(TimeSlot(1415))) // 45 mins before is occupied
        assertTrue(TimeSlot(1500).isOccupiedBy(TimeSlot(1430))) // 30 mins before is occupied
        assertTrue(TimeSlot(1500).isOccupiedBy(TimeSlot(1445))) // 15 mins before is occupied
        assertTrue(TimeSlot(1500).isOccupiedBy(TimeSlot(1500))) // Same time is occupied
        assertTrue(TimeSlot(1500).isOccupiedBy(TimeSlot(1515))) // 15 mins after is occupied
        assertTrue(TimeSlot(1500).isOccupiedBy(TimeSlot(1530))) // 30 mins after is occupied
        assertTrue(TimeSlot(1500).isOccupiedBy(TimeSlot(1545))) // 45 mins after is occupied
        assertFalse(TimeSlot(1500).isOccupiedBy(TimeSlot(1600))) // 60 mins after is not occupied
        // Starts from 1515
        assertFalse(TimeSlot(1515).isOccupiedBy(TimeSlot(1415)))
        assertTrue(TimeSlot(1515).isOccupiedBy(TimeSlot(1430)))
        assertTrue(TimeSlot(1515).isOccupiedBy(TimeSlot(1445)))
        assertTrue(TimeSlot(1515).isOccupiedBy(TimeSlot(1500)))
        assertTrue(TimeSlot(1515).isOccupiedBy(TimeSlot(1515)))
        assertTrue(TimeSlot(1515).isOccupiedBy(TimeSlot(1530)))
        assertTrue(TimeSlot(1515).isOccupiedBy(TimeSlot(1545)))
        assertTrue(TimeSlot(1515).isOccupiedBy(TimeSlot(1600)))
        assertFalse(TimeSlot(1515).isOccupiedBy(TimeSlot(1615)))

        // Starts from 153
        assertFalse(TimeSlot(1530).isOccupiedBy(TimeSlot(1430)))
        assertTrue(TimeSlot(1530).isOccupiedBy(TimeSlot(1445)))
        assertTrue(TimeSlot(1530).isOccupiedBy(TimeSlot(1500)))
        assertTrue(TimeSlot(1530).isOccupiedBy(TimeSlot(1515)))
        assertTrue(TimeSlot(1530).isOccupiedBy(TimeSlot(1530)))
        assertTrue(TimeSlot(1530).isOccupiedBy(TimeSlot(1545)))
        assertTrue(TimeSlot(1530).isOccupiedBy(TimeSlot(1600)))
        assertTrue(TimeSlot(1530).isOccupiedBy(TimeSlot(1615)))
        assertFalse(TimeSlot(1530).isOccupiedBy(TimeSlot(1630)))

        // Starts from 154
        assertFalse(TimeSlot(1545).isOccupiedBy(TimeSlot(1445)))
        assertTrue(TimeSlot(1545).isOccupiedBy(TimeSlot(1500)))
        assertTrue(TimeSlot(1545).isOccupiedBy(TimeSlot(1515)))
        assertTrue(TimeSlot(1545).isOccupiedBy(TimeSlot(1530)))
        assertTrue(TimeSlot(1545).isOccupiedBy(TimeSlot(1545)))
        assertTrue(TimeSlot(1545).isOccupiedBy(TimeSlot(1600)))
        assertTrue(TimeSlot(1545).isOccupiedBy(TimeSlot(1615)))
        assertTrue(TimeSlot(1545).isOccupiedBy(TimeSlot(1630)))
        assertFalse(TimeSlot(1545).isOccupiedBy(TimeSlot(1645)))
    }

}
