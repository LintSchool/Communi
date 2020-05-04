package com.ncorti.kotlin.template.app.Main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ncorti.kotlin.template.app.Main.Fragments.ChatFragment
import com.ncorti.kotlin.template.app.Main.Fragments.EventsFragment
import com.ncorti.kotlin.template.app.Feed.FeedFragment
import com.ncorti.kotlin.template.app.Main.Fragments.ProfileFragment

class MainPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    var feedFragment = FeedFragment.newInstance()
    var chatFragment = ChatFragment.newInstance()
    var eventsFragment = EventsFragment.newInstance()
    var profileFragment = ProfileFragment.newInstance()

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
       return when (position) {
            0 -> feedFragment
            1 -> chatFragment
            2 -> eventsFragment
            3 -> profileFragment
            else -> feedFragment
        }

    }


}