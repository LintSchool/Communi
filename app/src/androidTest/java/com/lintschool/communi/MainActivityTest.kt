package com.lintschool.communi

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.lintschool.communi.event.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java
    )
    /**
     * Example test function
     */
    @Test
    fun typeANumber_resultIsDisplayed() {
    }
}
