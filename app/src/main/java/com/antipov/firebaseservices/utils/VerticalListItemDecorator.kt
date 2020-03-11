package com.antipov.firebaseservices.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalListItemDecorator(private val padding: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val itemPosition = parent.getChildAdapterPosition(view)
        val adapter = parent.adapter ?: return
        if (itemPosition == RecyclerView.NO_POSITION) return

        when (itemPosition) {
            RecyclerView.NO_POSITION -> return
            0 -> outRect.set(padding, padding, padding, padding / 2)
            adapter.itemCount - 1 -> outRect.set(padding, padding / 2, padding, 0)
            else -> outRect.set(padding, padding / 2, padding, padding / 2)
        }
    }
}