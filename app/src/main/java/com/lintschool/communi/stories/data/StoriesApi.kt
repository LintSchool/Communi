package com.lintschool.communi.stories.data

class StoriesApi {

    fun getUsersStories(): List<UserStories> {
        return UserStories.USER_STORIES
    }

    fun replyToStory(message: Message): Boolean {
        message.sent = true
        return message.sent
    }
}
