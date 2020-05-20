package com.lintschool.communi.feed

import com.lintschool.communi.stories.UserStories

class FeedApis {

    fun getUsersStories(): List<UserStories> {
        return UserStories.USER_STORIES
    }

    fun getFeedPosts(): List<Post>{
        return Post.postsList
    }
}