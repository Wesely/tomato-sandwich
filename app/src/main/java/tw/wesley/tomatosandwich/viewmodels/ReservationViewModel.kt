package tw.wesley.tomatosandwich.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import tw.wesley.tomatosandwich.model.Reservation
import tw.wesley.tomatosandwich.repos.IReservationRepository
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val reservationRepository: IReservationRepository
) : ViewModel() {

    val reservationFlow: Flow<Set<Reservation>> = reservationRepository.getReservationsFlow()

}