package com.lintschool.communi.stories.data

data class Message(
    val senderId: Int,
    val receiverId: Int,
    val message: String,
    var sent: Boolean = false
)
