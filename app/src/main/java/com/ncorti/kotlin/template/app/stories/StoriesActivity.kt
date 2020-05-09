package com.ncorti.kotlin.template.app.stories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ncorti.kotlin.template.app.R
import com.ncorti.kotlin.template.app.utils.loadImageRes
import kotlinx.android.synthetic.main.activity_stories.*

class StoriesActivity : AppCompatActivity() {

    val storiesAdapter = StoriesAdapter()
    val stories = UserStories.getUserStoriesList()
    lateinit var timer: CountDownTimer

    var userIndex = 0
    var storyIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stories)

        setup()
    }

    private fun setup() {

        timer = object : CountDownTimer(10000, 1000) {

            override fun onFinish() {
                //Play next, if not next finish activity
                if (storyIndex == stories[userIndex].stories.size - 1) {
                    if (userIndex == stories.size - 1) {
                        finish()
                    } else {
                        stories[userIndex].playing = false
                        stories[userIndex].playingIndex = -1
                        storiesAdapter.notifyItemChanged(userIndex)

                        userIndex ++
                        storyIndex = 0
                        loadCurrentStory(userIndex, storyIndex)
                    }
                } else {
                    stories[userIndex].playingIndex = -1
                    storiesAdapter.notifyItemChanged(userIndex)

                    storyIndex++
                    loadCurrentStory(userIndex, storyIndex)
                }
            }

            override fun onTick(p0: Long) {
            }
        }


        stories_rv.apply {
            adapter = this@StoriesActivity.storiesAdapter
            layoutManager = LinearLayoutManager(this@StoriesActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        storiesAdapter.apply {

            onItemClick = {

                timer.cancel()

                stories[userIndex].playing = false
                notifyItemChanged(userIndex)

                storyIndex = 0
                userIndex = it


                loadCurrentStory(userIndex, storyIndex)
            }

            submitList(stories)
        }

        loadCurrentStory(userIndex, storyIndex)

        reply_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (charSequence.toString().isNotEmpty()) {
                    send_iv.alpha = 1F
                } else {
                    send_iv.alpha = 0.2F
                }
            }
        })
    }

    private fun loadCurrentStory(userIndex: Int, storyIndex: Int) {
        story_iv.loadImageRes(stories[userIndex].stories[storyIndex].storyRes)

        stories[userIndex].playing = true
        stories[userIndex].playingIndex = storyIndex

        storiesAdapter.notifyItemChanged(userIndex)
        timer.start()

    }

    override fun onStop() {
        super.onStop()
    }

    companion object {

        fun startIntent(context: Context): Intent {
            return Intent(context, StoriesActivity::class.java)
        }
    }
}
