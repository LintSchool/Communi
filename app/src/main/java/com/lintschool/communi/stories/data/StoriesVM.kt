package com.lintschool.communi.stories.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StoriesVM(val storiesUSeCase: StoriesUseCase) : ViewModel() {

    private val storiesImpl = MutableLiveData<List<UserStories>>()
    val stories: LiveData<List<UserStories>> = storiesImpl

    fun getStories(index: Int) {
        storiesImpl.value = storiesUSeCase.getStoriesFromPosition(index)
    }

    fun sendReply(userId: Int, message: String) {
        storiesUSeCase.sendReply(userId, message)
    }
}
