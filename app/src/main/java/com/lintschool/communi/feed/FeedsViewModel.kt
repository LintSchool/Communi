package com.lintschool.communi.feed

class FeedsViewModel(val feedsUseCase: FeedsUseCase) {
    fun getUserId() = feedsUseCase.getUserId()
    fun getPosts() = feedsUseCase.getPosts()
    fun getStories() = feedsUseCase.getStories()
}