package com.lintschool.communi.feed

data class Post(
    var id: String,
    var imagePath: Int?,
    var userName: String,
    var userImagePath: Int,
    var postText: String
)
