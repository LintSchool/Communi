package com.lintschool.communi.event
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lintschool.communi.R
import com.lintschool.communi.event.data.Event

class EventsBottmSheet : BottomSheetDialogFragment() {
    private var suggestionEventList: MutableList<Event> = mutableListOf()
    private var aroundEventList: MutableList<Event> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.bottom_sheet, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
