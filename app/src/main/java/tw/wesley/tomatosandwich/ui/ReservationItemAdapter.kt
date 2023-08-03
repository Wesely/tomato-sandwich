package tw.wesley.tomatosandwich.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.wesley.tomatosandwich.databinding.ListitemBookedTableBinding
import tw.wesley.tomatosandwich.model.Reservation

class ReservationItemAdapter(
    val reservations: MutableList<Reservation>
) : RecyclerView.Adapter<ReservationItemAdapter.ReservationItemViewHolder>() {

    class ReservationItemViewHolder(private val binding: ListitemBookedTableBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reservation: Reservation) {
            binding.tvPartySize.text = reservation.partySize.toString()
            binding.tvGuestName.text = reservation.guestName
            binding.tvTimeSlot.text = reservation.timeSlot.toFormattedTimeString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationItemViewHolder {
        val binding = ListitemBookedTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservationItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservationItemViewHolder, position: Int) {
        holder.bind(reservations[position])
    }

    override fun getItemCount(): Int = reservations.size

}
