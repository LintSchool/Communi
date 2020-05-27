package com.lintschool.communi.chat.repository

import com.lintschool.communi.chat.entity.Chat
import com.lintschool.communi.chat.entity.ChatMessage
import java.io.File

class ChatRepository(userId : Int)
{
    fun getChats() : List<Chat>
    {

        val chatList : List<Chat> = ArrayList()


        return chatList
    } // fun of getChats


    fun getChatDetails(chatId : Int) : List<ChatMessage>
    {

        val chatDetails : List<ChatMessage> = ArrayList()


        return chatDetails
    } // fun of getChatDetails


    fun sendTextMessage (text : String)
    {

    } // fun of sendTextMessage

    fun sendImages (images : List<File>)
    {

    } // fun of sendImages

    fun sendVoiceNote (voiceNote : File)
    {

    } // fun of sendVoiceNote



}