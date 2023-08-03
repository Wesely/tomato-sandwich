package tw.wesley.tomatosandwich.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import timber.log.Timber
import tw.wesley.tomatosandwich.R
import tw.wesley.tomatosandwich.databinding.FragmentMainBinding
import tw.wesley.tomatosandwich.ui.adadpters.ReservationItemAdapter
import tw.wesley.tomatosandwich.viewmodels.ReservationViewModel

class MainReservationsFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    // Use the shared ViewModel, This property can be accessed only after this Fragment is attached
    private val viewModel: ReservationViewModel by activityViewModels()

    private lateinit var adapter: ReservationItemAdapter

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

        // Initialize the RecyclerView
        binding.rvReservationList.layoutManager = LinearLayoutManager(context)
        adapter = ReservationItemAdapter(mutableListOf()) { reservation ->
            Timber.d("reservation/onClicked: $reservation")
            val action = MainReservationsFragmentDirections.actionMainFragmentToDetails(reservation)
            findNavController().navigate(action)
        }
        binding.rvReservationList.adapter = adapter

        lifecycleScope.launch {
            viewModel.reservationFlow.distinctUntilChanged().collectLatest { reservations ->
                Timber.d("subscribe/resvFlow:$reservations")

                // Update the RecyclerView adapter
                adapter.reservations.clear()
                adapter.reservations.addAll(reservations)
                adapter.notifyDataSetChanged() // BAD, but ...yeah
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
