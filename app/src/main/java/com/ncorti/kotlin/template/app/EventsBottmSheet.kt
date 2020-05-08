package com.ncorti.kotlin.template.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ncorti.kotlin.template.app.data.Event
import kotlinx.android.synthetic.main.bottom_sheet.*

class EventsBottmSheet : BottomSheetDialogFragment() {

    private var suggestionEventList: MutableList<Event> = mutableListOf()
    private var aroundEventList: MutableList<Event> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        rvSuggestions.layoutManager = LinearLayoutManager(activity)
//        rvSuggestions.itemAnimator = DefaultItemAnimator()
////        rvSuggestions.addItemDecoration(
////            DefaultDividerItemDecoration
////                (
////                activity,
////                DividerItemDecoration.VERTICAL,
////                36
////            )
////        )
//        val suggestedEventAdapter = SuggestedEventAdapter(suggestionEventList)
//        rvSuggestions.adapter = suggestedEventAdapter


    }

}