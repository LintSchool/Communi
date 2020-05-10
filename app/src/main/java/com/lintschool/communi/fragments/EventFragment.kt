package com.lintschool.communi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lintschool.communi.EventsBottmSheet
import com.lintschool.communi.R
import com.lintschool.communi.adapters.AroundEventAdapter
import com.lintschool.communi.adapters.SuggestedEventAdapter
import com.lintschool.communi.data.Event
import kotlinx.android.synthetic.main.bottom_sheet.*

class EventFragment : Fragment() {
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var arroundLayoutManager: LinearLayoutManager
    var suggestedEventList: MutableList<Event> = mutableListOf()
    var aroundEventList: MutableList<Event> = mutableListOf()
    var eventsBottmSheet: EventsBottmSheet =
        EventsBottmSheet()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_event, container, false)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvSuggestions.layoutManager = layoutManager
        suggestedEventList.add(0, Event("YF Aftershow Party", "Today", ""))
        suggestedEventList.add(1, Event("Football Match", "Tomorrow", ""))
        val suggestedEventAdapter =
            SuggestedEventAdapter(
                suggestedEventList,
                context!!
            )
        rvSuggestions.adapter = suggestedEventAdapter
        arroundLayoutManager = LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
        rvEventsAround.layoutManager = arroundLayoutManager
        aroundEventList.add(0, Event("YF Aftershow Party", "Today", ""))
        aroundEventList.add(1, Event("Football Match", "Tomorrow", ""))
        val aroundEventAdapter =
            AroundEventAdapter(aroundEventList)
        rvEventsAround.adapter = aroundEventAdapter
    }
}
