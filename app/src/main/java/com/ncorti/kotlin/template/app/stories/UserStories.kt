package com.ncorti.kotlin.template.app.stories

import com.ncorti.kotlin.template.app.R

data class UserStories(
    val userName: String,
    val userImageRes: Int,
    val stories : List<Story>,
    var playing: Boolean = false,
    var playingIndex: Int = -1
) {
    companion object {

        fun getUserStoriesList() : List<UserStories> {
            return listOf(
                UserStories("Eve Johnson", R.drawable.download, listOf(Story(R.drawable.download), Story(R.drawable.flowers), Story(R.drawable.download1)), true),
                UserStories("Eve Johnson", R.drawable.desktop, listOf(Story(R.drawable.desktop))),
                UserStories("Eve Johnson", R.drawable.flowers, listOf(Story(R.drawable.download1), Story(R.drawable.download1), Story(R.drawable.download1)))
            )
        }
    }
}

data class Story(
    val storyRes: Int
)