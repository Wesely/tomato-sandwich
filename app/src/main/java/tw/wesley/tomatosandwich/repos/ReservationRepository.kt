package tw.wesley.tomatosandwich.repos

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import tw.wesley.tomatosandwich.model.Reservation
import tw.wesley.tomatosandwich.model.TimeSlot
import java.util.SortedSet

/**
 * This Repo represents for the data layer, but it simply stores reservation as a SortedSet<Reservation>
 */
class ReservationRepository : IReservationRepository {

    private var _reservations: SortedSet<Reservation> = sortedSetOf()
    private var _reservationsFlow = MutableStateFlow<Set<Reservation>>(_reservations.toSet())
    private val availableTimeSlotsList: MutableList<TimeSlot> = INIT_TIME_SLOTS.toMutableList()

    override fun getReservations(): Set<Reservation> {
        return _reservations
    }

    override fun getReservationsFlow(): Flow<Set<Reservation>> {
        return _reservationsFlow
    }

    override fun addReservation(reservation: Reservation): Boolean {
        val result = _reservations.add(reservation)
        if (result) {
            _reservationsFlow.value = _reservations.toSet()
            availableTimeSlotsList.removeAll { it.isOccupied(reservation.timeSlot) }
        }
        return result
    }

    override fun getAvailableTimeSlots(): List<TimeSlot> {
        return availableTimeSlotsList
    }

    companion object {
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
