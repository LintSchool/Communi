package com.lintschool.communi.feed

import com.lintschool.communi.stories.StoriesRepository

class FeedsUseCase(val feedsRepository: FeedRepository, val feedStoriesRepositories: FeedStoriesRepositories) {
    fun getPosts() = feedsRepository.getAllPosts()
    fun getUserId() = feedsRepository.getUserId()
    fun getStories() = feedStoriesRepositories.getStories()
}
