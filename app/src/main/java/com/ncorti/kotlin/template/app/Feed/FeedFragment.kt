package com.ncorti.kotlin.template.app.Feed

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager

import com.ncorti.kotlin.template.app.R
import com.ncorti.kotlin.template.app.camera.CameraActivity
import com.ncorti.kotlin.template.app.stories.StoriesActivity
import kotlinx.android.synthetic.main.fragment_feed.*
import java.io.File

class FeedFragment : Fragment() {

    lateinit var storiesAdapter: StoriesAdapter
    lateinit var storiesDataList : MutableList<Image>

    companion object {
        fun newInstance(): FeedFragment = FeedFragment()
        private val IMAGE_ACTIVITY_REQUEST_CODE = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
    }

    fun setUp() {

        feedTitle.text = getString(R.string.feed_title) + "ya 2y 7aga."

        storiesDataList = mutableListOf<Image>(
            Image(1, R.drawable.ic_profile),
            Image(2, R.drawable.ic_profile),
            Image(3, R.drawable.ic_profile),
            Image(4, R.drawable.ic_profile),
            Image(5, R.drawable.ic_profile),
            Image(6, R.drawable.ic_profile)
        )

        storiesAdapter = StoriesAdapter()
        storiesAdapter.submitList(storiesDataList)
        storiesRV.adapter = storiesAdapter
        storiesRV.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        storiesAdapter.onItmClick = { itemView ->
            startActivity(CameraActivity.startIntent(requireContext()))
        }

        feedSubTitle.setOnClickListener {
            startActivityForResult(CameraActivity.startIntent(this.activity!!), IMAGE_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    var newStory = Image(7,((data.getSerializableExtra(CameraActivity.CAPTURED_IMAGE) as File).absolutePath).toInt())
//                    storiesDataList.add(newStory)
//                    storiesAdapter.submitList(null)
//                    storiesAdapter.submitList(storiesDataList)
                }
            }
        }
    }

}
