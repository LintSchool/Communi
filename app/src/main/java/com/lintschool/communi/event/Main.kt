package com.lintschool.communi.event
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lintschool.communi.R
import com.lintschool.communi.event.fragments.EventFragment
import kotlinx.android.synthetic.main.activity_main_.*

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.feed -> {
            }
            R.id.chats -> {
            }
            R.id.events -> {
                val fragment = EventFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
            }
        }
        false
    }
}
