package com.ncorti.kotlin.template.app.Feed

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class PostItemDecorator(var offset: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
//        var layoutManagerPositions = (parent.layoutManager as StaggeredGridLayoutManager).findLastCompletelyVisibleItemPositions(IntArray((parent.layoutManager as StaggeredGridLayoutManager).spanCount))


        var layoutManagerPosition = (parent.layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()

        outRect.apply {
            if(layoutManagerPosition %2 == 0){
                left = offset
                right = offset/2
                top = offset
                bottom = offset
            }else{
                left = offset/2
                right = offset
                top = offset
                bottom = offset
            }
        }
    }
}