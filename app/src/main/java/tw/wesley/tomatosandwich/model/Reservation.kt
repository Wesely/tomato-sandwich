package tw.wesley.tomatosandwich.model


data class Reservation(
    val timeSlot: TimeSlot,
    val guestName: String,
    val partySize: Int,
    val phone: String = "",
    val notes: String = "",
) : Comparable<Reservation> {

    /**
     * Implement [Comparable] so we can let out SortedSet sort it by time
     */
    override fun compareTo(other: Reservation): Int {
        return this.timeSlot.compareTo(other.timeSlot)
    }

}
