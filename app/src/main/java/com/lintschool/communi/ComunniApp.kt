package com.lintschool.communi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ComunniApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}