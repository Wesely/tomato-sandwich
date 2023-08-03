package tw.wesley.tomatosandwich.repos

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import tw.wesley.tomatosandwich.model.Reservation
import tw.wesley.tomatosandwich.model.TimeSlot
import tw.wesley.tomatosandwich.repos.IReservationRepository.Companion.INIT_TIME_SLOTS
import java.util.SortedSet

/**
 * This Repo represents for the data layer, but it simply stores reservation as a SortedSet<Reservation>
 */
class ReservationRepository : IReservationRepository {

    private var _reservations: SortedSet<Reservation> = sortedSetOf()
    private var _reservationsFlow = MutableStateFlow<List<Reservation>>(_reservations.toList())
    private val availableTimeSlotsList: MutableList<TimeSlot> = INIT_TIME_SLOTS.toMutableList()

    override fun getReservations(): Set<Reservation> {
        return _reservations
    }

    override fun getReservationsFlow(): Flow<List<Reservation>> {
        return _reservationsFlow
    }

    override fun addReservation(reservation: Reservation): Boolean {
        val result = _reservations.add(reservation)
        if (result) {
            _reservationsFlow.value = _reservations.toList()
            availableTimeSlotsList.removeAll { it.isOccupied(reservation.timeSlot) }
        }
        return result
    }

    override fun getAvailableTimeSlots(): List<TimeSlot> {
        return availableTimeSlotsList
    }

}
