package com.lintschool.communi.stories.data

import java.lang.IllegalArgumentException

class StoriesRepository(val storiesApi: StoriesApi, val sharedPreferencesWrapper: SharedPreferencesWrapper) {

    val userId = sharedPreferencesWrapper.getInt(SharedPreferencesWrapper.USER_ID_KEY)

    fun sendReply(receiverId: Int, message: String): Boolean {
        return storiesApi.replyToStory(
            Message(
                userId,
                receiverId,
                message
            )
        )
    }

    fun getStoriesFromPosition(position: Int): List<UserStories> {
        val startingPosition = position - 1
        val stories = storiesApi.getUsersStories()
        if (startingPosition != -1 && startingPosition < stories.size) {
            return stories.subList(startingPosition, stories.size).apply {
                this[0].playing = true
            }
        } else {
            throw IllegalArgumentException("Position should be within stories list")
        }
    }
}
