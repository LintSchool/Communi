package com.lintschool.communi.event.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lintschool.communi.R
import com.lintschool.communi.event.EventActivity
import com.lintschool.communi.event.data.Event
import kotlinx.android.synthetic.main.item_suggested_event.view.*

class SuggestedEventAdapter(
    private var eventList: List<Event> = mutableListOf(),
    val context: Context
) : RecyclerView.Adapter<SuggestedEventAdapter.SuggestedEventViewHolder>() {
    var onItemClick: ((Event) -> Unit)? = null
    inner class SuggestedEventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(event: Event) {
            itemView.eventName.text = event.name
            itemView.eventDate.text = event.date
        }
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(eventList[adapterPosition])
                var intent = Intent(
                    context,
                    EventActivity::class.java
                )
                context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestedEventViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_suggested_event, parent, false)
        return SuggestedEventViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return eventList.size
    }
    override fun onBindViewHolder(
        holder: SuggestedEventViewHolder,
        position: Int
    ) {
        holder.bindData(eventList[position])
    }
}
