package com.ncorti.kotlin.template.app.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.davidriad.community.fragments.DetailsFragment
import com.davidriad.community.fragments.FeedFragment
import com.davidriad.community.fragments.GuestListFragment

class ViewPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val count = 3

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment =
            DetailsFragment()
        when (position) {
            0 -> fragment = DetailsFragment()
            1 -> fragment = GuestListFragment()
            2 -> fragment = FeedFragment()
        }

        return fragment
    }

    override fun getCount(): Int {
        return count
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab " + (position + 1)
    }
}