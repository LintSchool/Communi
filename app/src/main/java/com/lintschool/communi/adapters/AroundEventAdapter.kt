package com.lintschool.communi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lintschool.communi.R
import com.lintschool.communi.data.Event
import kotlinx.android.synthetic.main.item_arround_event.view.*

class AroundEventAdapter(
    private var eventList: List<Event> = mutableListOf()
) : RecyclerView.Adapter<AroundEventAdapter.ArroundEventViewHolder>() {
    inner class ArroundEventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(event: Event) {
            itemView.eventName.text = event.name
            itemView.eventDate.text = event.date
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):
        ArroundEventViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_arround_event, parent, false)
            return ArroundEventViewHolder(itemView)
        }
    override fun getItemCount(): Int {
        return eventList.size
    }
    override fun onBindViewHolder(holder: ArroundEventViewHolder, position: Int) {
        holder.bindData(eventList[position])
    }
}
