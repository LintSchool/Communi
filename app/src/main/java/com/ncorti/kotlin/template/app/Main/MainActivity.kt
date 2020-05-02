package com.ncorti.kotlin.template.app.Main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
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
                        getDrawable(R.drawable.ic_feed)
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
                        getDrawable(R.drawable.ic_feed)
                    }
                }
            }).attach()
    }
}

//private val notificationUtil: NotificationUtil by lazy { NotificationUtil(this) }
//button_compute.setOnClickListener {
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
//}
