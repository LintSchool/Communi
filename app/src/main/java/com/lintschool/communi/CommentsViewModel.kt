package com.lintschool.communi

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class CommentsViewModel @ViewModelInject constructor(val repository: CommentsRepository) : ViewModel() {
    fun getSomething() = ""

}