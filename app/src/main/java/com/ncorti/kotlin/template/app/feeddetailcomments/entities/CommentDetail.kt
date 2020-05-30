package com.ncorti.kotlin.template.app.feeddetailcomments.entities

data class CommentDetail (
    var commentId: Int = 0,
    var commentOwnerName: String = "",
    var commentBody: String = "",
    var commentImagePath: String = "",
    var commentLikes: Int = 0
)