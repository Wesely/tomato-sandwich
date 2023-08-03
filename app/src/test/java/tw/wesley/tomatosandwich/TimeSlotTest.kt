package tw.wesley.tomatosandwich

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import tw.wesley.tomatosandwich.model.TimeSlot

class TimeSlotTest {

    @Test
    fun testIsOccupiedBy() {
        // Starts from 1500
        assertTrue(TimeSlot(1500).isOccupiedBy(TimeSlot(1415))) // 45 mins after 1415 is occupied
        assertFalse(TimeSlot(1500).isOccupiedBy(TimeSlot(1400))) // An hour after 1400 is fine
        assertFalse(TimeSlot(1500).isOccupiedBy(TimeSlot(1345))) // More than an hour after 1345 is fine

        // Starts from 1515
        assertTrue(TimeSlot(1515).isOccupiedBy(TimeSlot(1430))) // 45 mins after 1430 is occupied
        assertFalse(TimeSlot(1515).isOccupiedBy(TimeSlot(1415))) // An hour after 1415 is fine
        assertFalse(TimeSlot(1515).isOccupiedBy(TimeSlot(1400))) // More than an hour after 1400 is fine

        // Starts from 1530
        assertTrue(TimeSlot(1530).isOccupiedBy(TimeSlot(1445))) // 45 mins after 1445 is occupied
        assertFalse(TimeSlot(1530).isOccupiedBy(TimeSlot(1430))) // An hour after 1430 is fine
        assertFalse(TimeSlot(1530).isOccupiedBy(TimeSlot(1415))) // More than an hour after 1415 is fine

        // Starts from 1545
        assertTrue(TimeSlot(1545).isOccupiedBy(TimeSlot(1500))) // 45 mins after 1500 is occupied
        assertTrue(TimeSlot(1545).isOccupiedBy(TimeSlot(1515))) // 30 mins after 1515 is occupied
        assertTrue(TimeSlot(1545).isOccupiedBy(TimeSlot(1520))) // 25 mins after 1520 is occupied
        assertTrue(TimeSlot(1545).isOccupiedBy(TimeSlot(1525))) // 20 mins after 1525 is occupied
        assertFalse(TimeSlot(1545).isOccupiedBy(TimeSlot(1445))) // An hour after 1445 is fine
        assertFalse(TimeSlot(1545).isOccupiedBy(TimeSlot(1430))) // More than an hour after 1430 is fine

    }

}
