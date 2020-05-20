package com.lintschool.communi.stories

import com.lintschool.communi.R

data class UserStories(
    val userName: String,
    val userId: Int,
    val userImageRes: Int,
    val stories: List<Story>,
    var playing: Boolean = false,
    var playingIndex: Int = -1,
    var addStory : Boolean
) {
    companion object {

        val USER_STORIES = listOf(
            UserStories(
                "Eve Johnson", 1, R.drawable.download,
                listOf(
                    Story(R.drawable.download),
                    Story(R.drawable.flowers),
                    Story(R.drawable.download1)
                ),
                true,
                -1,
                false
            ),
            UserStories(
                "Eve Johnson", 2, R.drawable.desktop,
                listOf(Story(R.drawable.desktop)),
                true,
                -1,
                false
            ),
            UserStories(
                "Eve Johnson", 3, R.drawable.flowers,
                listOf(
                    Story(R.drawable.download1),
                    Story(R.drawable.download1),
                    Story(R.drawable.download1)
                ),
                true,
                -1,
                false
            )
        )
    }
}

data class Story(
    val storyRes: Int
)
