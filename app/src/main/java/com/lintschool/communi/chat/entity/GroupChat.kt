package com.lintschool.communi.chat.entity


data class GroupChat(
    val contactList : List<Contact>,
    val groupName : String? = null
) : Chat()