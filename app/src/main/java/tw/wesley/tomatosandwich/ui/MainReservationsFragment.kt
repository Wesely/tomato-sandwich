package tw.wesley.tomatosandwich.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tw.wesley.tomatosandwich.R
import tw.wesley.tomatosandwich.databinding.FragmentMainBinding
import tw.wesley.tomatosandwich.viewmodels.ReservationViewModel

class MainReservationsFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    // Use the shared ViewModel, This property can be accessed only after this Fragment is attached
    private val viewModel: ReservationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.btnCreate.setOnClickListener {
            // action to start reservation
            findNavController().navigate(R.id.action_start_create_reservation)
        }

        lifecycleScope.launch {
            viewModel.reservationFlow.collectLatest {
                Log.d("subscribe/resvFlow", it.toString())
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
