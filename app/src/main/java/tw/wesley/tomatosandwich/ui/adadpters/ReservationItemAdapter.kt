package tw.wesley.tomatosandwich.ui.adadpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.wesley.tomatosandwich.databinding.ListitemBookedTableBinding
import tw.wesley.tomatosandwich.model.Reservation

class ReservationItemAdapter(
    val reservations: MutableList<Reservation>,
    private val onReservationClickedCallback: (Reservation) -> Unit
) : RecyclerView.Adapter<ReservationItemAdapter.ReservationItemViewHolder>() {

    class ReservationItemViewHolder(private val binding: ListitemBookedTableBinding, private val onReservationClickedCallback: (Reservation) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reservation: Reservation) {
            binding.tvPartySize.text = reservation.partySize.toString()
            binding.tvGuestName.text = reservation.guestName
            binding.tvTimeSlot.text = reservation.timeSlot.toFormattedTimeString()
            binding.root.setOnClickListener { onReservationClickedCallback.invoke(reservation) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationItemViewHolder {
        val binding = ListitemBookedTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservationItemViewHolder(binding, onReservationClickedCallback)
    }

    override fun onBindViewHolder(holder: ReservationItemViewHolder, position: Int) {
        holder.bind(reservations[position])
    }

    override fun getItemCount(): Int = reservations.size

}
