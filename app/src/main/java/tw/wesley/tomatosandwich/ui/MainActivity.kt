package tw.wesley.tomatosandwich.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tw.wesley.tomatosandwich.databinding.ActivityMainBinding
import tw.wesley.tomatosandwich.viewmodels.ReservationViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val reservationViewModel: ReservationViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreate.setOnClickListener {
            println("?????????")
            Log.d("???", "<<<<<")
            // Creating new instances of dialog fragments on demand should be fine since the previous one shall be destroy and reset
            val bottomSheet = CreateReservationBottomSheetDialogFragment()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }

        lifecycleScope.launch {
            reservationViewModel.reservationFlow.collectLatest {
                Log.d("subscribe/resvFlow", it.toString())
            }
        }
    }
}