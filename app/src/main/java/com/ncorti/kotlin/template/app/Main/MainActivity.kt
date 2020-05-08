package com.ncorti.kotlin.template.app.Main

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ncorti.kotlin.template.app.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
    }

    fun setUp() {
        var viewPagerAdapter = MainPagerAdapter(this)
        mainViewPager.adapter = viewPagerAdapter
        mainViewPager.setCurrentItem(0, false)
        mainViewPager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT

        TabLayoutMediator(
            mainTabView,
            mainViewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.icon = when (position) {
                    0 -> {
                        getDrawable(R.drawable.ic_grid)
                    }
                    1 -> {
                        getDrawable(R.drawable.ic_message_square)
                    }
                    2 -> {
                        getDrawable(R.drawable.ic_activity)
                    }
                    3 -> {
                        getDrawable(R.drawable.ic_profile)
                    }
                    else -> {
                        getDrawable(R.drawable.ic_grid)
                    }
                }
            }
        ).attach()

        mainTabView.getTabAt(0)?.select()
        mainTabView.getTabAt(0)?.text = getString(R.string.feed)
        mainTabView.getTabAt(0)?.icon?.setTint(ContextCompat.getColor(this@MainActivity, R.color.colorPrimary))

        mainTabView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                tab?.icon?.setTint(ContextCompat.getColor(this@MainActivity, R.color.color_gray))
                tab?.text = ""
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.text = when (tab?.position) {
                    0 -> {
                        getString(R.string.feed)
                    }
                    1 -> {
                        getString(R.string.events)
                    }
                    2 -> {
                        getString(R.string.chat)
                    }
                    3 -> {
                        getString(R.string.profile)
                    }
                    else -> {
                        getString(R.string.feed)
                    }
                }
                tab?.icon?.setTint(ContextCompat.getColor(this@MainActivity, R.color.colorPrimary))
            }
        })
        // To allow equal width for each tab, while (TabLayout.MODE_SCROLLABLE)
        val slidingTabStrip = mainTabView.getChildAt(0) as ViewGroup
        for (i in 0 until mainTabView.getTabCount()) {
            val tab: View = slidingTabStrip.getChildAt(i)
            val layoutParams =
                tab.getLayoutParams() as LinearLayout.LayoutParams
            layoutParams.weight = 1f
            tab.setLayoutParams(layoutParams)
        }
    }
}

// private val notificationUtil: NotificationUtil by lazy { NotificationUtil(this) }
// button_compute.setOnClickListener {
//    val input = edit_text_factorial.text.toString().toInt()
//    val result = FactorialCalculator.computeFactorial(input).toString()
//
//    text_result.text = result
//    text_result.visibility = View.VISIBLE
//
//    notificationUtil.showNotification(
//        context = this,
//        title = getString(R.string.notification_title),
//        message = result
//    )
// }
