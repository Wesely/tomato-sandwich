package tw.wesley.tomatosandwich.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import tw.wesley.tomatosandwich.R
import tw.wesley.tomatosandwich.databinding.FragmentPartySizeBinding
import tw.wesley.tomatosandwich.viewmodels.CreateReservationViewModel

class PartySizeSelectionFragment : Fragment() {

    private var _binding: FragmentPartySizeBinding? = null
    private val binding get() = _binding!!

    // Use the shared ViewModel
    private val viewModel: CreateReservationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPartySizeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.buttonsContainer) {
            for (numPartySize in 1..5) {
                // Create new MaterialButton
                val button = MaterialButton(context).apply {
                    text = numPartySize.toString()
                    setTextAppearance(androidx.constraintlayout.widget.R.style.TextAppearance_AppCompat_Title)
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        resources.getDimensionPixelSize(R.dimen.button_height_large)
                    ).apply {
                        topMargin = resources.getDimensionPixelSize(R.dimen.button_margin)
                        bottomMargin = resources.getDimensionPixelSize(R.dimen.button_margin)
                    }
                    // Setting outlined style programmatically
                    strokeColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.lettuce))

                    // Set the onclick behav here
                    setOnClickListener {
                        onPartySizeSelected(numPartySize)
                    }
                }
                // Add button to the container
                addView(button)
            }
        }
    }


    private fun onPartySizeSelected(partySize: Int) {
        viewModel.partySize = partySize
        findNavController().navigate(R.id.action_party_size_to_details)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}