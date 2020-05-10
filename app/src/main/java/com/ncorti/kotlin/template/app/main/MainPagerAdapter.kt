package com.ncorti.kotlin.template.app.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ncorti.kotlin.template.app.feed.FeedFragment
import com.ncorti.kotlin.template.app.main.fragments.ChatFragment
import com.ncorti.kotlin.template.app.main.fragments.EventsFragment
import com.ncorti.kotlin.template.app.main.fragments.ProfileFragment

class MainPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    var feedFragment = FeedFragment.newInstance()
    var chatFragment = ChatFragment.newInstance()
    var eventsFragment = EventsFragment.newInstance()
    var profileFragment = ProfileFragment.newInstance()

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            "0".toInt() -> feedFragment
            "1".toInt() -> chatFragment
            "2".toInt() -> eventsFragment
            "3".toInt() -> profileFragment
            else -> feedFragment
        }
    }
}
