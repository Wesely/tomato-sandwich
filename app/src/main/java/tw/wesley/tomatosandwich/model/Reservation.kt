package tw.wesley.tomatosandwich.model


data class Reservation(
    var timeSlot: TimeSlot,  // Step 1
    var partySize: Int,      // Step 2
    var guestName: String,   // step 3
    var phone: String = "",  // step 3
    var notes: String = "",  // step 3
) : Comparable<Reservation> {

    /**
     * Implement [Comparable] so we can let out SortedSet sort it by time
     */
    override fun compareTo(other: Reservation): Int {
        return this.timeSlot.compareTo(other.timeSlot)
    }

}
