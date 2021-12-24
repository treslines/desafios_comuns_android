package com.progdeelite.dca.design

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.progdeelite.dca.R
import com.progdeelite.dca.util.setVisible

fun getListDivider(
    res: Resources,
    recyclerView: RecyclerView,
    llm: LinearLayoutManager,
): RecyclerView.ItemDecoration {
    val dividerItemDecoration = DividerItemDecoration(recyclerView.context, llm.orientation)

    ResourcesCompat.getDrawable(res, R.drawable.white_divider, null)?.let {
        dividerItemDecoration.setDrawable(it)
    }
    return dividerItemDecoration
}

fun getPriorityColor(context: Context, priority: Int): Drawable? {
    return AppCompatResources.getDrawable(
        context,
        mapOf(
            1 to R.color.red,
            2 to R.color.orange,
            3 to R.color.yellow,
        )[priority] ?: -1
    )
}

fun showFilledOrEmptyView(
    condition: Boolean,
    filledView: View, emptyView: View,
) {
    if (condition) {
        filledView.setVisible(true)
        emptyView.setVisible(false)
    } else {
        filledView.setVisible(false)
        emptyView.setVisible(true)
    }
}