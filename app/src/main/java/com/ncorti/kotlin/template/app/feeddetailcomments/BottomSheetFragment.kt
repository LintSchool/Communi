package com.example.rxjavademo

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lintschool.communi.R

class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_bottom_sheet_feed_comments, container,
            false
        )
        return view
    }

    companion object {
        const val heightPercent = 0.5f
        const val expandHeightPercent = 0.85f
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (view!!.parent as View).background =
            context!!.getDrawable(R.drawable.upper_rounded_color_bottom_sheet)

        dialog?.setOnShowListener { dialog ->

            val d = dialog as BottomSheetDialog

            val bottomSheet =
                dialog.findViewById<FrameLayout>(R.id.design_bottom_sheet)
            val coordinatorLayout = bottomSheet?.parent as CoordinatorLayout
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheet.viewTreeObserver?.addOnGlobalLayoutListener {
                bottomSheet.viewTreeObserver.removeOnGlobalLayoutListener {}
                bottomSheetBehavior.peekHeight = getPopupHeight(heightPercent)
                val params = bottomSheet.layoutParams
                params.height = getPopupHeight(expandHeightPercent)
                bottomSheet.layoutParams = params
                coordinatorLayout.parent?.requestLayout()
            }
        }
    }

    private fun getPopupHeight(percent: Float): Int {
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        return (displayMetrics.heightPixels * percent).toInt()
    }
}
