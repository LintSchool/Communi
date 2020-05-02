package com.ncorti.kotlin.template.app.Feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ncorti.kotlin.template.app.R
import kotlinx.android.synthetic.main.stories_rv_item.view.*

class StoriesAdapter() : ListAdapter<Image, StoryViewHolder>(DiffUtilsCallback()) {

    lateinit var onItmClick : ((view : View) -> Unit)

    class DiffUtilsCallback() : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var itemView = layoutInflater.inflate(R.layout.stories_rv_item, parent, false)
        return StoryViewHolder(itemView, onItmClick)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class StoryViewHolder(itemView: View,  var onItemClicked: ((view : View) -> Unit)? = null) : RecyclerView.ViewHolder(itemView) {

    fun bind(itemData: Image) {
        if (adapterPosition == 0) {
            itemView.addStoryBtn.visibility = View.VISIBLE
        } else {
            itemView.storyImage.setImageResource(itemData.imagePath)
        }

        itemView.storyContainer.setOnClickListener { onItemClicked?.invoke(itemView) }


    }
}