package com.lintschool.communi.feed

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class FeedsInjector {

    fun getSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getPreferencesWrapper(context: Context): SharedPreferencesWrapper {
        return SharedPreferencesWrapper(getSharedPreferences(context))
    }

    fun getFeedsApi(): FeedApis {
        return FeedApis()
    }

    fun getFeedsRepository(context: Context): FeedRepository {
        return FeedRepository(getPreferencesWrapper(context), getFeedsApi())
    }


}