package tw.wesley.tomatosandwich.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @param time consequntial integers,
 */
@Parcelize
data class TimeSlot(
    val time: Int,
    var available: Boolean = true,
) : Comparable<TimeSlot>, Parcelable {

    /**
     * @param other: another booked TimeSlot
     * Each reservation lasts for 1 hour, if 13:00 is booked, 13:00, 13:15, 13:30, 13:45 ar all occupied
     * @return true if this timeSlot within an hour after the booked TimeSlot
     */
    fun isOccupiedBy(other: TimeSlot): Boolean {
        // if 13:45 is booked, 13:45, 14:30 is occupied.
        // if 13:45 is booked, 14:45 is NOT occupied. We don't need to clean the table :)
        // this 85 represents for [1 hour minus 15 minutes => 100 -15 = 85]
        return (time - other.time) >= 0 && (time - other.time) <= 85
    }

    override fun compareTo(other: TimeSlot): Int {
        return time - other.time
    }

    // We're so lucky that the restaurant is not available in the morning.
    // So the time slots are all in PM format.
    fun toFormattedTimeString(): String {
        val hh = time / 100
        val mm = time % 100
        return "%02d:%02d".format(hh, mm)
    }
}