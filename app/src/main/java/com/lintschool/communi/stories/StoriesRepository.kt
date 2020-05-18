package com.lintschool.communi.stories

import java.lang.IllegalArgumentException

class StoriesRepository(val storiesApi: StoriesApi, val sharedPreferencesWrapper: SharedPreferencesWrapper) {

    val userId = sharedPreferencesWrapper.getInt(SharedPreferencesWrapper.USER_ID_KEY)

    fun sendReply(receiverId: Int, message: String): Boolean {
        return storiesApi.replyToStory(Message(userId, receiverId, message))
    }

    fun getStoriesFromPosition(position: Int): List<UserStories> {
        val stories = storiesApi.getUsersStories()
        if (position != 0 && position < stories.size) {
            return stories.subList(position, stories.lastIndex)
        } else {
            throw IllegalArgumentException("Position should be within stories list")
        }
    }
}
