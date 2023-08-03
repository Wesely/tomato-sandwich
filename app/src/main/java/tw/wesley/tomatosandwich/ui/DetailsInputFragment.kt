package tw.wesley.tomatosandwich.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import tw.wesley.tomatosandwich.R
import tw.wesley.tomatosandwich.databinding.FragmentDetailsInputBinding
import tw.wesley.tomatosandwich.viewmodels.CreateReservationViewModel

class DetailsInputFragment : Fragment() {

    private var _binding: FragmentDetailsInputBinding? = null
    private val binding get() = _binding!!

    // Use the shared ViewModel
    private val viewModel: CreateReservationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnSave.setOnClickListener {
                viewModel.addReservation()
                findNavController().navigate(R.id.actionFinishCreateReservation)
            }
            textInputEditTextName.doOnTextChanged { text, start, before, count ->
                Log.d("textInputEditTextName", "doOnTextChanged/$text")
                viewModel.guestName = text.toString()
            }
            textInputEditTextPhone.doOnTextChanged { text, start, before, count ->
                Log.d("textInputEditTextPhone", "doOnTextChanged/$text")
                viewModel.phone = text.toString()
            }
            textInputEditTextNotes.doOnTextChanged { text, start, before, count ->
                Log.d("textInputEditTextNotes", "doOnTextChanged/$text")
                viewModel.notes = text.toString()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}