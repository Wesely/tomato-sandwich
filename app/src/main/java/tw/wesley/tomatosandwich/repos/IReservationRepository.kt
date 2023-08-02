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
}
