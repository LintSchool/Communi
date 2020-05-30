package com.ncorti.kotlin.template.app.feeddetailcomments.repository

import com.ncorti.kotlin.template.app.feeddetailcomments.entities.CommentDetail
import com.ncorti.kotlin.template.app.feeddetailcomments.entities.FeedDetails

class CommentRepository {
    var feedDetailsList = mutableListOf<FeedDetails>()

    fun getFeed(feedId: Int): FeedDetails {
        return feedDetailsList.find { it.feedId == feedId }!!
    }

    fun addComment(feedId: Int, commentDetail: CommentDetail) {
        getFeed(feedId).comments.add(commentDetail)
    }

    fun addLike(feedId: Int, commentId: Int) {
        getFeed(feedId).comments.find { it.commentId == commentId }!!.commentLikes++
    }
}
