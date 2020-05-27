package com.lintschool.communi.chat.entity

data class ChatMessage (
    val chatId : Int = 0,
    val contact: Contact,
    val message : String? = null,
    val imageList : List<String>? = null,
    val voiceNote : String? = null
)