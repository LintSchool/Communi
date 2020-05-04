package com.ncorti.kotlin.template.app.Feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ncorti.kotlin.template.app.R
import kotlinx.android.synthetic.main.add_story_rv_item.view.*
import kotlinx.android.synthetic.main.stories_rv_item.view.*
import kotlinx.android.synthetic.main.stories_rv_item.view.storyContainer
import kotlinx.android.synthetic.main.stories_rv_item.view.storyImage

class StoriesAdapter() : ListAdapter<Image, RecyclerView.ViewHolder>(DiffUtilsCallback()) {

    lateinit var onItmClick: ((view: View) -> Unit)
    lateinit var onAddMyStoryItmClick: ((view: View) -> Unit)

    companion object {
        var ADD_STORY_TYPE = 0
        var STORY = 1
    }

    override fun getItemViewType(position: Int): Int {

        return if (getItem(position).addStory) ADD_STORY_TYPE else STORY
    }

    class DiffUtilsCallback() : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return if (oldItem.addStory && newItem.addStory) oldItem.id == newItem.id else if (!oldItem.addStory && !newItem.addStory) oldItem.id == newItem.id else false

        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return if (oldItem.addStory && newItem.addStory) oldItem == newItem else if (!oldItem.addStory && !newItem.addStory) oldItem == newItem else false
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var itemView: View
        lateinit var viewHolder: RecyclerView.ViewHolder

        when (viewType) {
            ADD_STORY_TYPE -> {
                itemView = layoutInflater.inflate(R.layout.add_story_rv_item, parent, false)
                viewHolder = AddStoryViewHolder(itemView, onAddMyStoryItmClick)
            }
            STORY -> {
                itemView = layoutInflater.inflate(R.layout.stories_rv_item, parent, false)
                viewHolder = StoryViewHolder(itemView, onItmClick)
            }
        }


        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AddStoryViewHolder -> holder.bind(getItem(position))
            is StoryViewHolder -> holder.bind(getItem(position))
        }
    }

}

class StoryViewHolder(itemView: View, var onItemClicked: ((view: View) -> Unit)? = null) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(itemData: Image) {

        itemView.storyImage.setImageResource(itemData.imagePath!!)

        itemView.storyContainer.setOnClickListener { onItemClicked?.invoke(itemView) }


    }
}

class AddStoryViewHolder(
    itemView: View,
    var onAddStoryItemClicked: ((view: View) -> Unit)? = null
) : RecyclerView.ViewHolder(itemView) {

    fun bind(itemData: Image) {

        itemView.addStoryBtn.visibility = View.VISIBLE
        if (itemData.imagePath != null)
            itemView.storyImage.setImageResource(itemData.imagePath!!)


        itemView.storyContainer.setOnClickListener { onAddStoryItemClicked?.invoke(itemView) }


    }
}