package com.lintschool.communi.di

import android.content.Context
import android.content.SharedPreferences
import com.lintschool.communi.APIHelper
import com.lintschool.communi.CommentsRepository
import com.lintschool.communi.CommentsViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MagicModule(private val context: Context) {

    @Provides
    fun provideAPIHelper(): APIHelper = APIHelper()

    @Singleton
    @Provides
    fun provideSharedPreference(): SharedPreferences =
        context.getSharedPreferences("name", Context.MODE_PRIVATE)

    @Provides
    fun provideRepository(
        apiHelper: APIHelper,
        sharedPreferences: SharedPreferences
    ): CommentsRepository =
        CommentsRepository(sharedPreferences,apiHelper )

    @Provides
    fun provideViewModelFactory(repository: CommentsRepository): CommentsViewModelFactory =
        CommentsViewModelFactory(repository)
}