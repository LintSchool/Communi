package com.lintschool.communi

import android.content.Context
import android.content.SharedPreferences

object CommentsInjection {

    const val URL = "myUrl"
    private var sharedPreferences: SharedPreferences? = null

    fun provideAPIHelper(): APIHelper = APIHelper()

    fun provideSharedPreference(context: Context): SharedPreferences =
        if (sharedPreferences == null)
            context.getSharedPreferences("name", Context.MODE_PRIVATE)
                .apply {
                    this@CommentsInjection.sharedPreferences = this
                }
        else
            sharedPreferences!!


    fun provideRepository(
        sharedPreferences: SharedPreferences,
        apiHelper: APIHelper
    ): CommentsRepository =
        CommentsRepository(sharedPreferences, apiHelper)

}