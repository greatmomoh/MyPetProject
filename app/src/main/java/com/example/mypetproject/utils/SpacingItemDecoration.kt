package com.example.mypetproject.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(
    private val horizontalSpacing: Int,
    private val verticalSpacing: Int,
    private val doubleFirstItemLeftMargin: Boolean = true,
    private val doubleFirstItemRightMargin: Boolean = false,
    private val isVertical: Boolean = false
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == 0 && doubleFirstItemLeftMargin) {
            outRect.left = horizontalSpacing * 2
        } else if (isVertical) {
            outRect.left = horizontalSpacing / 2
        } else {
            outRect.left = horizontalSpacing
        }

        if (parent.getChildAdapterPosition(view) == state.itemCount - 1 && doubleFirstItemRightMargin) {
            outRect.right = this.horizontalSpacing * 2
        } else {
            outRect.right = this.horizontalSpacing / 2
        }
        outRect.top = this.verticalSpacing / 2
        outRect.bottom = this.verticalSpacing / 2
    }
}
