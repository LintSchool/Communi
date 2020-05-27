package com.lintschool.communi.chat.entity

abstract class Chat {
    val chatId: Int = 0
    val lastMessage: String? = null
    val lastTime: String? = null
    val isSeen: Boolean = false
    val unReadCounter: Int = 0
    val chatType: String = "single"
}



