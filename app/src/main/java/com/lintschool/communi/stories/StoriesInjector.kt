package com.lintschool.communi.stories

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object StoriesInjector {

    fun getSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getPreferencesWrapper(context: Context): SharedPreferencesWrapper {
        return SharedPreferencesWrapper(getSharedPreferences(context))
    }

    fun getStoriesApi(): StoriesApi {
        return StoriesApi()
    }

    fun getStoriesRepository(context: Context): StoriesRepository {
        return StoriesRepository(getStoriesApi(), getPreferencesWrapper(context))
    }
}
