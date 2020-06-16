package com.lintschool.communi.feed

import com.lintschool.communi.stories.SharedPreferencesWrapper
import com.lintschool.communi.stories.StoriesApi
import com.lintschool.communi.stories.UserStories

class FeedStoriesRepositories(val storiesApi: StoriesApi, val sharedPreferencesWrapper: SharedPreferencesWrapper){

    fun getStories(): List<UserStories> {
        return storiesApi.getUsersStories()

    }

}