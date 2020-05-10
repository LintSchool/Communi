package com.lintschool.communi.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lintschool.communi.fragments.DetailsFragment
import com.lintschool.communi.fragments.FeedFragment
import com.lintschool.communi.fragments.GuestListFragment

class ViewPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {
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

    companion object {
        private const val count = 3
    }
}
