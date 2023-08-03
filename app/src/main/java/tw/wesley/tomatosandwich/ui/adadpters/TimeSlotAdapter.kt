package tw.wesley.tomatosandwich.ui.adadpters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import tw.wesley.tomatosandwich.R
import tw.wesley.tomatosandwich.model.TimeSlot

class TimeSlotAdapter(
    private var timeSlots: List<TimeSlot>,
    private val onItemClickCallback: (TimeSlot) -> Unit
) : RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder>() {

    class TimeSlotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTimeSlot: TextView = itemView.findViewById(R.id.tvTimeSlot)
        val tvInvalid: TextView = itemView.findViewById(R.id.tvInvalid)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem_time_slot, parent, false)
        return TimeSlotViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimeSlotViewHolder, position: Int) {
        val timeSlot = timeSlots[position]
        holder.tvTimeSlot.text = timeSlot.toFormattedTimeString()
        holder.tvInvalid.isVisible = !timeSlot.available
        holder.itemView.setOnClickListener { onItemClickCallback(timeSlot) }
    }

    override fun getItemCount() = timeSlots.size


    fun updateData(newData: List<TimeSlot>) {
        this.timeSlots = newData
        // I'll ignore the range update, just update the whole dataset here.
        notifyDataSetChanged()
    }
}
