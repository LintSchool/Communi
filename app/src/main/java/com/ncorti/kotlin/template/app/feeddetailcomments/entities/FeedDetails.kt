package com.ncorti.kotlin.template.app.feeddetailcomments.entities

data class FeedDetails(
    var feedId: Int = 0,
    var feedBody: String = "",
    var feedOwner: String = "",
    var feedTime: String = "",
    var comments: MutableList<CommentDetail> = mutableListOf()
)