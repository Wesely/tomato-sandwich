package tw.wesley.tomatosandwich.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import tw.wesley.tomatosandwich.databinding.BottomSheetCreateReservationBinding


class CreateReservationBottomSheetDialogFragment : BottomSheetDialogFragment() {

    // This property is only valid between onCreateView and onDestroyView.
    private var _binding: BottomSheetCreateReservationBinding? = null

    // Everywhere else, use this `binding`
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetCreateReservationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}