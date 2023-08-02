package tw.wesley.tomatosandwich

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tw.wesley.tomatosandwich.model.Reservation
import tw.wesley.tomatosandwich.model.TimeSlot
import tw.wesley.tomatosandwich.viewmodels.ReservationViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val reservationViewModel: ReservationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            reservationViewModel.reservationFlow.collectLatest {
                Log.d("subscribe/resvFlow", it.toString())
            }
        }

        reservationViewModel.addReservation(
            Reservation(
                timeSlot = TimeSlot(1500, true),
                guestName = "SampleGuest",
                partySize = 5
            )
        )
    }
}