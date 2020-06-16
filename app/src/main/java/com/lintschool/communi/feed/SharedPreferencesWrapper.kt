package com.lintschool.communi.feed

import android.content.SharedPreferences

class SharedPreferencesWrapper(val sharedPreferences: SharedPreferences) {
    companion object {
        const val USER_ID_KEY = "user_id"
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }
}