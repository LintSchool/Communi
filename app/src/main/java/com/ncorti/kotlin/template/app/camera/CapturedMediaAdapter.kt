package com.ncorti.kotlin.template.app.camera

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ncorti.kotlin.template.app.R
import com.ncorti.kotlin.template.app.utils.loadImageUri
import kotlinx.android.synthetic.main.item_captured.view.*

class CapturedMediaAdapter :
    ListAdapter<Any, RecyclerView.ViewHolder>(CapturedMediaAdapter.CapturedImageDiffUtil()) {

    val VIEW_TYPE_MEDIA = 0
    val VIEW_TYPE_ADD = 1

    var onItemClick: ((position: Int) -> Unit)? = null
    var onItemRemove: ((position: Int) -> Unit)? = null
    var onAddClicked: (() -> Unit)? = null
    var lastSelected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView: View
        val holder: RecyclerView.ViewHolder

        when (viewType) {
            VIEW_TYPE_MEDIA -> {
                itemView = layoutInflater.inflate(R.layout.item_captured, parent, false)
                holder = MediaViewHolder(itemView)
            }

            VIEW_TYPE_ADD -> {
                itemView = layoutInflater.inflate(R.layout.item_add_photo, parent, false)
                holder = AddViewHolder(itemView)
            }

            else -> throw IllegalArgumentException("Undefined View Type")
        }

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? MediaViewHolder)?.bind(currentList[position] as CapturedImage)
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position] is CapturedImage) {
            VIEW_TYPE_MEDIA
        } else {
            VIEW_TYPE_ADD
        }
    }

    inner class MediaViewHolder(
        itemView: View
    ) :
        RecyclerView.ViewHolder(itemView) {

        init {
            itemView.capturedImageContainer.setOnClickListener {
                lastSelected = adapterPosition
                onItemClick?.invoke(
                    adapterPosition
                )
            }
            itemView.remove_iv.setOnClickListener { onItemRemove?.invoke(adapterPosition) }
        }

        fun bind(item: CapturedImage) {
            itemView.iv_captured.loadImageUri(item.imagePath)
        }
    }

    inner class AddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onAddClicked?.invoke()
            }
        }
    }

    class CapturedImageDiffUtil : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return (oldItem as? CapturedImage)?.imagePath == (newItem as? CapturedImage)?.imagePath
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return (oldItem as? CapturedImage) == (newItem as? CapturedImage)
        }
    }
}
