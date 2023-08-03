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
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tw.wesley.tomatosandwich.R
import tw.wesley.tomatosandwich.databinding.FragmentTimeSelectionBinding
import tw.wesley.tomatosandwich.ui.adadpters.TimeSlotAdapter
import tw.wesley.tomatosandwich.viewmodels.CreateReservationViewModel

class TimeSelectionFragment : Fragment() {

    private var _binding: FragmentTimeSelectionBinding? = null
    private val binding get() = _binding!!

    // Use the shared ViewModel
    private val viewModel: CreateReservationViewModel by activityViewModels()

    private lateinit var timeSlotAdapter: TimeSlotAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimeSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the adapter
        timeSlotAdapter = TimeSlotAdapter(listOf()) { timeSlot ->
            viewModel.timeSlot = timeSlot
            findNavController().navigate(R.id.actionTimeSelectionToPartySize)
        }

        // Set the RecyclerView's layout manager and adapter
        binding.rvReservationList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = timeSlotAdapter
        }

        // Launch a new coroutine in the lifecycleScope. This means the coroutine will be bind to the parent's lifecycle
        lifecycleScope.launch {
            viewModel.availableTimeSlotsFlow.collectLatest { timeSlots ->
                // Update the adapter with the new time slots
                timeSlotAdapter.updateData(timeSlots)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
