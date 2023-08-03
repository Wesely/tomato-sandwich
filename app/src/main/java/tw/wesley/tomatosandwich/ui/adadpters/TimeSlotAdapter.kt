package tw.wesley.tomatosandwich.ui.adadpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import tw.wesley.tomatosandwich.R
import tw.wesley.tomatosandwich.databinding.ListitemTimeSlotBinding
import tw.wesley.tomatosandwich.model.TimeSlot

class TimeSlotAdapter(
    private var timeSlots: List<TimeSlot>,
    private val onItemClickCallback: (TimeSlot) -> Unit
) : RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder>() {

    class TimeSlotViewHolder(private val binding: ListitemTimeSlotBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(timeSlot: TimeSlot, onItemClickCallback: (TimeSlot) -> Unit) {
            val context = binding.root.context
            binding.tvTimeSlot.text = timeSlot.toFormattedTimeString()
            binding.tvInvalid.isVisible = !timeSlot.available

            // You can also customize the color based on the availability
            if (timeSlot.available) {
                binding.cardView.setCardBackgroundColor(ResourcesCompat.getColor(context.resources, R.color.colorControlActivated, null))
                itemView.setOnClickListener { onItemClickCallback(timeSlot) }
            } else {
                binding.cardView.setCardBackgroundColor(ResourcesCompat.getColor(context.resources, R.color.colorControlNormal, null))
                itemView.setOnClickListener(null)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotViewHolder {
        val binding = ListitemTimeSlotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeSlotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeSlotViewHolder, position: Int) {
        holder.bind(timeSlots[position], onItemClickCallback)
    }

    override fun getItemCount() = timeSlots.size

    fun updateData(newData: List<TimeSlot>) {
        this.timeSlots = newData
        // I'll ignore the range update, just update the whole dataset here.
        notifyDataSetChanged()
    }
}
