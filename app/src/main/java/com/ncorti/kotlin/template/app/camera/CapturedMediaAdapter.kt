package com.ncorti.kotlin.template.app.camera

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ncorti.kotlin.template.app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_captured.view.*

class CapturedMediaAdapter : RecyclerView.Adapter<MediaViewHolder>() {

    lateinit var onItemClick: ((position: Int) -> Unit)
    lateinit var onItemRemove: ((position: Int) -> Unit)
    var imagesList = mutableListOf<CapturedImages>()

    fun setData(data: MutableList<CapturedImages>) {
        imagesList = data
    }

    override fun getItemCount(): Int = imagesList.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var itemView = layoutInflater.inflate(R.layout.item_captured, parent, false)
        return MediaViewHolder(itemView, onItemClick, onItemRemove)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(imagesList[position])
    }

}

class MediaViewHolder(
    itemView: View,
    var onItemClicked: ((position: Int) -> Unit),
    var onRemoveItem: ((position: Int) -> Unit)
) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(itemData: CapturedImages) {
        if (itemData.imagePath != "")
            Picasso.get().load(itemData.imagePath).into(itemView.captured_img)

        itemView.capturedImageContainer.setOnClickListener { onItemClicked?.invoke(adapterPosition) }
        itemView.remove_iv.setOnClickListener { onRemoveItem.invoke(adapterPosition) }

    }
}