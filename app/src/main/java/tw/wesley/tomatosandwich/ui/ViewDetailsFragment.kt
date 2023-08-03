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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


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
                tvTime.text = getString(R.string.time_details_format, reservation.timeSlot.toString())
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
