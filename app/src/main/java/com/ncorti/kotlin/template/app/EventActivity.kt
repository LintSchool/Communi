package com.ncorti.kotlin.template.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.ncorti.kotlin.template.app.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_event_details.*
import kotlinx.android.synthetic.main.event_details_bottom_sheet.*

class EventActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
// Add the three videos into an array


        backArrow.setOnClickListener {
            onBackPressed()
        }
        tabs.addTab(tabs.newTab().setText("Details"))
        tabs.addTab(tabs.newTab().setText("Guest list"))
        tabs.addTab(tabs.newTab().setText("Feed"))
//
        tabs.tabGravity = TabLayout.GRAVITY_FILL


        val viewPager = findViewById(R.id.viewpager) as ViewPager

        val tabLayout = findViewById(R.id.tabs) as TabLayout


        if (viewPager != null) {
            val adapter = ViewPagerAdapter(
                supportFragmentManager
            )
            viewPager.adapter = adapter
        }


        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))

        tabs!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


    }
}
