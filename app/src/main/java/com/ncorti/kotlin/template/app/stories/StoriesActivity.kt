package com.ncorti.kotlin.template.app.stories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.ncorti.kotlin.template.app.R
import kotlinx.android.synthetic.main.activity_stories.*

class StoriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stories)

        setup()
    }

    private fun setup() {
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

    companion object {

        fun startIntent(context: Context): Intent {
            return Intent(context, StoriesActivity::class.java)
        }
    }
}
