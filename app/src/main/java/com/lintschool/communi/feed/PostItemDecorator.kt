package com.lintschool.communi.feed

import android.graphics.Rect
import android.view.View
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

        var layoutManagerPositions = (parent.layoutManager as StaggeredGridLayoutManager).findLastCompletelyVisibleItemPositions(IntArray((parent.layoutManager as StaggeredGridLayoutManager).spanCount))

        outRect.apply {
            if (layoutManagerPositions.get(1) % 2 == 0) {
                left = offset
                right = offset / 2
                top = offset
                bottom = offset
            } else {
                left = offset / 2
                right = offset
                top = offset
                bottom = offset
            }
        }
    }
}
