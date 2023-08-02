package tw.wesley.tomatosandwich.model

/**
 * @param time consequntial integers,
 */
data class TimeSlot(
    val time: Int,
    var available: Boolean = true,
) : Comparable<TimeSlot> {

    /**
     * @param other: another booked TimeSlot
     * @return true if this timeSlot within an hour after the booked TimeSlot
     */
    fun isOccupied(other: TimeSlot): Boolean {
        // if 13:45 is booked, 14:30 is occupied.
        // if 13:45 is booked, 14:45 is NOT occupied. We don't need to clean the table :)
        // TODO: Unit test for this
        return (time - other.time) > 0 && (time - other.time) <= 85
    }

    override fun compareTo(other: TimeSlot): Int {
        return time - other.time
    }

    // We're so lucky that the restaurant is not available in the morning.
    // So the time slots are all in PM format.
    override fun toString(): String {
        val hh = time / 100
        val mm = time % 100
        return "%2d:%2d".format(hh, mm)
    }
}