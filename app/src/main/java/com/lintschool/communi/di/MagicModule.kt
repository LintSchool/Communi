package com.lintschool.communi.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.lintschool.communi.APIHelper
import com.lintschool.communi.CommentsRepository
import com.lintschool.communi.ComunniApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object MagicModule {
    @Provides
    fun provideAPIHelper(): APIHelper = APIHelper()

    @Provides
    @ActivityScoped
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("name", Context.MODE_PRIVATE)

    @Provides
    fun provideRepository(
        apiHelper: APIHelper,
        sharedPreferences: SharedPreferences
    ): CommentsRepository =
        CommentsRepository(sharedPreferences, apiHelper)
}



