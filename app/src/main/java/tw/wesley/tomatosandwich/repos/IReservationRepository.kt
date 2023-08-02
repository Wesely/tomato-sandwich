package tw.wesley.tomatosandwich.repos

import kotlinx.coroutines.flow.Flow
import tw.wesley.tomatosandwich.model.Reservation
import tw.wesley.tomatosandwich.model.TimeSlot

/**
 * Per description, we need to provide these 3 method to the Staff in the restaurant.
 * There's no way to cancel the appointment but close the app, lol.
 */
interface IReservationRepository {
    fun getReservations(): Set<Reservation>
    fun getReservationsFlow(): Flow<Set<Reservation>>
    fun addReservation(reservation: Reservation): Boolean
    fun getAvailableTimeSlots(): List<TimeSlot>

    companion object {
        // This shall be consists even we have another implementation for this interface.
        // So I think it's find to put it here.
        val INIT_TIME_SLOTS: List<TimeSlot> = mutableListOf<TimeSlot>().apply {
            for (hh in 15..21) {
                for (mm in listOf(0, 15, 30, 45)) {
                    this.add(TimeSlot(hh * 100 + mm))
                }
            }
            this.add(TimeSlot(2200))
        }
    }
}
