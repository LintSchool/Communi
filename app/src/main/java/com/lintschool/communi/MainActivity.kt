package com.lintschool.communi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lintschool.communi.di.DaggerMagicComponent
import com.lintschool.communi.di.MagicModule
import com.ncorti.kotlin.template.app.feeddetailcomments.entities.CommentDetail
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: CommentsViewModelFactory


    private val commentsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(CommentsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMagicComponent.builder()
            .magicModule(MagicModule(this))
            .build()
            .poke(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        commentsViewModel.getSomething()
    }
}
