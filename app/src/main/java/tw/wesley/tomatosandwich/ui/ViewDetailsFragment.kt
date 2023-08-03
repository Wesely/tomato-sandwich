package tw.wesley.tomatosandwich.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tw.wesley.tomatosandwich.R
import tw.wesley.tomatosandwich.databinding.FragmentViewDetailsBinding
import tw.wesley.tomatosandwich.model.Reservation

class ViewDetailsFragment : Fragment() {

    private var _binding: FragmentViewDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewDetailsBinding.inflate(inflater, container, false)
        val reservation = requireArguments().getParcelable<Reservation>("reservation")
        if (reservation != null) {
            with(binding) {
                tvGuestName.text = reservation.guestName
                tvPartySize.text = getString(R.string.party_size_format, reservation.partySize)
                tvTime.text = getString(R.string.time_details_format, reservation.timeSlot.toFormattedTimeString())
                tvPhone.text = reservation.phone
                tvVisitNotes.text = reservation.notes
                btnClose.setOnClickListener {
                    findNavController().popBackStack()
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
