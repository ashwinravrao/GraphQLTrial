package com.ashwinrao.graphqltrial.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.ashwinrao.graphqltrial.util.UnitConversion.dpToPx

object ItemDecoration {

    fun addItemDecoration(
        context: Context,
        recyclerView: RecyclerView,
        spanCount: Int, @Nullable horizontalBias: Float?
    ) {
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val position = parent.getChildAdapterPosition(view)
                val spacingHorizontal = dpToPx(context, 4f)
                val spacingVertical = dpToPx(context, 16f)

                if (position >= 0) {
                    val column = position % spanCount

                    outRect.left =
                        (spacingHorizontal - column * spacingHorizontal / spanCount + if (horizontalBias == null) 0 else dpToPx(
                            context,
                            horizontalBias
                        ))
                    outRect.right =
                        ((column + 1) * spacingHorizontal / spanCount + if (horizontalBias == null) 0 else dpToPx(
                            context,
                            horizontalBias
                        ))

                    if (position < spanCount) {
                        outRect.top = spacingVertical
                    }
                    outRect.bottom = spacingVertical
                } else {
                    outRect.left = 0
                    outRect.right = 0
                    outRect.top = 0
                    outRect.bottom = 0
                }
            }
        })
    }

}