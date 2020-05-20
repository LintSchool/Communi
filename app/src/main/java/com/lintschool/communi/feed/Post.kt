package com.lintschool.communi.feed

import com.lintschool.communi.R

data class Post(
    var id: Int,
    var imagePath: Int?,
    var userName: String,
    var userImagePath: Int,
    var postText: String
){
    companion object{
        var postsList = listOf<Post>(
            Post(
                1,
                null,
                "Kate",
                R.drawable.download,
                "ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga"
            ),
            Post(
                2,
                R.drawable.download1,
                "Kate",
                R.drawable.download,
                "ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga"
            ),
            Post(
                id = 3,
                imagePath = null,
                userName = "Kate",
                userImagePath = R.drawable.download,
                postText = "ay 7aga ay 7aga ay 7aga ay 7agaga ay 7aga ay 7aga"
            ),
            Post(
                4,
                null,
                "Kate",
                R.drawable.download,
                " ay 7aga ay 7aga ay 7aga "
            ),
            Post(
                5,
                R.drawable.download1,
                "Kate",
                R.drawable.download,
                "ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga"
            ),
            Post(
                6,
                R.drawable.download1,
                "Kate",
                R.drawable.download,
                "ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga"
            ),
            Post(
                id = 7,
                imagePath = null,
                userName = "Kate",
                userImagePath = R.drawable.download,
                postText = "aga ay 7aga ay 7aga ay 7aga ay 7aga"
            )
        )
    }
}
