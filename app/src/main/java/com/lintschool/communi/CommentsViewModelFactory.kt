package com.lintschool.communi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.lintschool.communi.di.DaggerMagicComponent
import javax.inject.Inject

class CommentsViewModelFactory(
    private val repository: CommentsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CommentsRepository::class.java)
            .newInstance(repository)
    }
}