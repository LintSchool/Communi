package com.lintschool.communi.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lintschool.communi.R
import com.lintschool.communi.camera.CameraActivity
import com.lintschool.communi.stories.StoriesActivity
import com.lintschool.communi.stories.UserStories
import kotlinx.android.synthetic.main.feedbottomsheet.*
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    lateinit var storiesAdapter: StoriesAdapter
    lateinit var postsAdapter: PostsAdapter
    var feedViewModel = FeedsInjector().getFeedsViewModel(this.requireContext())
    lateinit var postsList : List<Post>
    lateinit var storiesList : List<UserStories>

    companion object {
        fun newInstance(): FeedFragment = FeedFragment()
        var postItemDecoratorOffset = 8
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
    }

    fun setUp() {
        postsList = feedViewModel.getPosts()
        storiesList = feedViewModel.getStories()
        feedTitle.text = getString(R.string.feed_title) + "ya 2y 7aga."

        feedSubTitle.setOnClickListener {
        }

        postsAdapter = PostsAdapter()
        postsAdapter.submitList(postsList)
        feedsRV.adapter = postsAdapter
        var layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        feedsRV.layoutManager = layoutManager
        feedsRV.addItemDecoration(PostItemDecorator(postItemDecoratorOffset))

        postsAdapter.onPostClick = { itemView ->
            Toast.makeText(
                activity,
                postsList[storiesRV.getChildAdapterPosition(itemView)].id.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }

        storiesAdapter = StoriesAdapter()
        storiesAdapter.submitList(storiesList)
        storiesRV.adapter = storiesAdapter
        storiesRV.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        storiesAdapter.onItmClick = { position ->
            startActivity(StoriesActivity.startIntent(requireContext(), position))
        }

        storiesAdapter.onAddMyStoryItmClick = { itemView ->
            startActivity(CameraActivity.startIntent(requireContext()))
        }

        feedSubTitle.setOnClickListener {
            val bottomSheetDialog =
                context?.let { it1 -> BottomSheetDialog(it1, R.style.BottomSheetDialogTheme) }

            val bottomSheetView = LayoutInflater.from(context).inflate(
                R.layout.feedbottomsheet, bottomsheetcontainers
            )
            bottomSheetDialog!!.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }
    }
}
