package tw.wesley.tomatosandwich.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import tw.wesley.tomatosandwich.model.Reservation
import tw.wesley.tomatosandwich.model.TimeSlot
import tw.wesley.tomatosandwich.repos.IReservationRepository
import javax.inject.Inject

@HiltViewModel
class CreateReservationViewModel @Inject constructor(
    private val reservationRepository: IReservationRepository
) : ViewModel() {

    // private setter, public getter of flow
    private val _availableTimeSlotsFlow = MutableStateFlow<List<TimeSlot>>(listOf())
    val availableTimeSlotsFlow: StateFlow<List<TimeSlot>> = _availableTimeSlotsFlow

    init {
        fetchAvailableTimeSlots()
    }

    // it should be private variables and public setters for encapsulation of data in the ViewModel as best practice
    // but I'm exposing them directly here to save some time.
    var timeSlot: TimeSlot? = null
    var partySize: Int? = null
    var guestName: String? = null
    var phone: String? = null
    var notes: String? = null

    private fun resetReservationParams() {
        timeSlot = null
        partySize = null
        guestName = null
        phone = null
        notes = null
    }

    private fun fetchAvailableTimeSlots() {
        val availableTimeSlots = reservationRepository.getTimeSlots()
        _availableTimeSlotsFlow.value = availableTimeSlots
    }

    /**
     * Create an [Reservation] and add it to our repo.
     * The param vars would be reset after this step.
     * I can check the timeSlot collision again here but I'm pretending it's fine since I only provide the available slots for the user to select in 1st step.
     * [ReservationViewModel] of MainActivity shall get the updated flow immediately.
     */
    fun addReservation(): Boolean {
        return if (timeSlot != null && partySize != null && guestName != null) {
            val reservation = Reservation(timeSlot!!, partySize!!, guestName!!, phone.orEmpty(), notes.orEmpty())
            reservationRepository.addReservation(reservation).also {
                // clear the data, even it's failed (very heartless)
                resetReservationParams()
                // update the available timeSlot
                fetchAvailableTimeSlots()
            }
        } else {
            // usually I'll tell which param is missing or even go back to corresponding state, but I'm ignoring it here
            throw (IllegalStateException(
                "Can only create a reservation after we have all required data. " +
                        "timeSlot = $timeSlot," +
                        "partySize = $partySize," +
                        "guestName = $guestName,"
            ))
        }
    }
}