package com.lintschool.communi.stories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lintschool.communi.R
import com.lintschool.communi.utils.loadImageRes
import kotlinx.android.synthetic.main.add_story_rv_item.view.*
import kotlinx.android.synthetic.main.item_current_story.view.*

class StoriesAdapter : ListAdapter<UserStories, RecyclerView.ViewHolder>(UserStoriesDiffUtil()) {

    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View
        val holder: RecyclerView.ViewHolder

        when (viewType) {
            VIEW_TYPE_PLAYING -> {
                view = inflater.inflate(R.layout.item_current_story, parent, false)
                holder = PlayingStoriesViewHolder(view)
            }

            VIEW_TYPE_NORMAL -> {
                view = inflater.inflate(R.layout.stories_rv_item, parent, false)
                holder = StoriesViewHolder(view)
            }

            else -> throw IllegalArgumentException("Undefined View type")
        }

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? PlayingStoriesViewHolder)?.bind(currentList[position])
        (holder as? StoriesViewHolder)?.bind(currentList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].playing) VIEW_TYPE_PLAYING else VIEW_TYPE_NORMAL
    }

    inner class StoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(adapterPosition)
            }
        }

        fun bind(story: UserStories) {
            itemView.storyImage.loadImageRes(story.userImageRes)
            itemView.storyImage.alpha = itemsAlpha
        }
    }

    inner class PlayingStoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(story: UserStories) {
            itemView.user_iv.loadImageRes(story.userImageRes)
            itemView.user_name_tv.text = story.userName
            itemView.story_counter_tv.text = itemView.context.getString(
                R.string.stories_counter,
                story.playingIndex + 1,
                story.stories.size
            )
        }
    }

    class UserStoriesDiffUtil : DiffUtil.ItemCallback<UserStories>() {
        override fun areItemsTheSame(oldItem: UserStories, newItem: UserStories): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserStories, newItem: UserStories): Boolean {
            return oldItem == newItem
        }
    }

    companion object {

        const val VIEW_TYPE_PLAYING = 0
        const val VIEW_TYPE_NORMAL = 1

        const val itemsAlpha = 0.4F
    }
}
