package com.ncorti.kotlin.template.app.Feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ncorti.kotlin.template.app.R
import kotlinx.android.synthetic.main.posts_rv_item.view.*

class PostsAdapter() : ListAdapter<Post, PostsViewHolder>(DiffUtilCallback()) {

    lateinit var onPostClick: ((view: View) -> Unit)

    class DiffUtilCallback() : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.posts_rv_item, parent, false)

        return PostsViewHolder(view, onPostClick)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PostsViewHolder(itemView: View, var onClick: ((view: View) -> Unit)) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(itemData: Post) {
        itemView.postContainer.setBackgroundResource(R.drawable.color_primary_gradient_square)
        itemView.profile_image.setImageResource(itemData.userImagePath)
        itemView.userName.text = itemData.userName
        itemView.postTV.text = itemData.postText
        if (itemData.imagePath != null) {
            itemView.constraintContainer.setImageResource(itemData.imagePath!!)
        }

        itemView.postContainer.setOnClickListener {
            onClick.invoke(itemView)
        }
    }
}
