package com.lintschool.communi.stories.data

class StoriesUseCase(val storiesRepository: StoriesRepository) {

    fun getStoriesFromPosition(position: Int) = storiesRepository.getStoriesFromPosition(position)

    fun sendReply(receiverId: Int, message: String) = storiesRepository.sendReply(receiverId, message)
}
