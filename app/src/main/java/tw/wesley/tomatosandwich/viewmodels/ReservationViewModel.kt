package tw.wesley.tomatosandwich.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import tw.wesley.tomatosandwich.model.Reservation
import tw.wesley.tomatosandwich.model.TimeSlot
import tw.wesley.tomatosandwich.repos.IReservationRepository
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val reservationRepository: IReservationRepository
) : ViewModel() {

    val reservationFlow: Flow<Set<Reservation>> = reservationRepository.getReservationsFlow()

    private val _availableTimeSlotsFlow = MutableStateFlow<List<TimeSlot>>(listOf())
    val availableTimeSlotsFlow: StateFlow<List<TimeSlot>> = _availableTimeSlotsFlow

    init {
        fetchAvailableTimeSlots()
    }

    fun fetchAvailableTimeSlots() {
        val availableTimeSlots = reservationRepository.getAvailableTimeSlots()
        _availableTimeSlotsFlow.value = availableTimeSlots
    }

    fun addReservation(reservation: Reservation): Boolean {
        Log.d("adding resetvation", reservation.toString())
        return reservationRepository.addReservation(reservation)
    }
}