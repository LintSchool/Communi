package com.lintschool.communi.stories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lintschool.communi.R
import com.lintschool.communi.stories.data.StoriesVM
import com.lintschool.communi.stories.data.UserStories
import com.lintschool.communi.utils.loadImageRes
import kotlinx.android.synthetic.main.activity_stories.*
import org.koin.android.viewmodel.ext.android.viewModel

class StoriesActivity : AppCompatActivity() {

    val storiesAdapter = StoriesAdapter()
    lateinit var timer: CountDownTimer
    val viewModel: StoriesVM by viewModel()
    lateinit var stories: List<UserStories>

    var userIndex = 0
    var storyIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stories)

        setup()
    }

    private fun setup() {
        val startingPosition: Int

        intent.hasExtra(POSITION_KEY).let {
            startingPosition = intent.getIntExtra(POSITION_KEY, 0)
            viewModel.getStories(startingPosition)
        }

        timer = object : CountDownTimer(TIMER_DURATION, TIMER_INTERVAL) {

            override fun onFinish() {
                if (storyIndex == stories[userIndex].stories.size - 1) {
                    if (userIndex == stories.size - 1) {
                        finish()
                    } else {
                        stories[userIndex].playing = false
                        stories[userIndex].playingIndex = -1
                        storiesAdapter.notifyItemChanged(userIndex)

                        userIndex++
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
                Log.d(StoriesActivity::class.java.simpleName, "Stories timer $p0")
            }
        }

        stories_rv.apply {
            adapter = this@StoriesActivity.storiesAdapter
            layoutManager =
                LinearLayoutManager(this@StoriesActivity, LinearLayoutManager.HORIZONTAL, false)
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
        }

        viewModel.stories.observe(
            this,
            Observer {
                stories = it
                storiesAdapter.submitList(stories)
                loadCurrentStory(userIndex, storyIndex)
            }
        )

        reply_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                Log.v("afterTextChanged", "afterTextChanged")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.v("beforeTextChanged", "beforeTextChanged")
            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (charSequence.toString().isNotEmpty()) {
                    btn_send.alpha = ACTIVE_BTN_ALPHA
                    btn_send.isEnabled = true
                } else {
                    btn_send.alpha = INACTIVE_BTN_ALPHA
                    btn_send.isEnabled = false
                }
            }
        })

        btn_send.setOnClickListener {
            viewModel.sendReply(stories[userIndex].userId, reply_et.text.toString())
        }
    }

    private fun loadCurrentStory(userIndex: Int, storyIndex: Int) {
        story_iv.loadImageRes(stories[userIndex].stories[storyIndex].storyRes)

        stories[userIndex].playing = true
        stories[userIndex].playingIndex = storyIndex

        storiesAdapter.notifyItemChanged(userIndex)
        timer.start()
    }

    companion object {

        const val ACTIVE_BTN_ALPHA = 1F
        const val INACTIVE_BTN_ALPHA = 0.2F

        const val TIMER_DURATION = 10000L
        const val TIMER_INTERVAL = 1000L

        private const val POSITION_KEY = "position"

        fun startIntent(context: Context, position: Int): Intent {
            val intent = Intent(context, StoriesActivity::class.java)
            intent.putExtra(POSITION_KEY, position)
            return intent
        }
    }
}
