package com.lintschool.communi.feed

class FeedRepository (val sharedPreferenceWrapper : SharedPreferencesWrapper, val feedApi : FeedApis) {

    fun getUserId() = sharedPreferenceWrapper.getInt(SharedPreferencesWrapper.USER_ID_KEY)

    fun getAllPosts() = feedApi.getFeedPosts()
}