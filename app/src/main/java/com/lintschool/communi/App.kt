package com.lintschool.communi

import android.app.Application
import com.lintschool.communi.stories.StoriesInjector
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(StoriesInjector.storiesModule))
    }
}
