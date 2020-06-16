package com.lintschool.communi.feed

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.lintschool.communi.stories.StoriesInjector
import com.lintschool.communi.stories.StoriesRepository

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

    fun getFeedStoriesRepository(context: Context): FeedStoriesRepositories {
        return FeedStoriesRepositories(StoriesInjector.getStoriesApi(), StoriesInjector.getPreferencesWrapper(context))
    }

    fun getFeedsUseCase(context: Context) : FeedsUseCase{
        return FeedsUseCase(getFeedsRepository(context), getFeedStoriesRepository(context))
    }

    fun getFeedsViewModel(context: Context) : FeedsViewModel{
        return FeedsViewModel(getFeedsUseCase(context))
    }


}