package com.progdeelite.dca.item_decoration

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.progdeelite.dca.R
import com.progdeelite.dca.custom_view.CustomMainMenuItem
import com.progdeelite.dca.recyclerview.CustomMainMenuItemAdapter

class ItemDecoratorWithText(
    parent: RecyclerView, // Passando aqui porque a ideia é usar nas extensões
    title: String = ""
) : RecyclerView.ItemDecoration() {

    val layout: View

    init {
        val inflater = LayoutInflater.from(parent.context)
        layout = inflater.inflate(R.layout.item_decorator_text_divider, parent, false) as TextView
        layout.text = title
        layout.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
    }
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        // BASICAMENTE SO DESENHAMOS NO TOPO DA PRIEMIRA VIEW - CUSTOMIZE COMO QUEIRA
        val lp = layout.layoutParams as ViewGroup.MarginLayoutParams

        layout.layout(parent.left, 0, parent.right - lp.rightMargin - lp.leftMargin,layout.measuredHeight)
        for (i in 0 until parent.childCount) {
            val view = parent.getChildAt(i)
            if (parent.getChildAdapterPosition(view) == 0) {
                c.save()
                val height = layout.measuredHeight
                val top = view.top - height - lp.bottomMargin
                c.translate(50F /*lp.leftMargin.toFloat()*/, top.toFloat())
                layout.draw(c)
                c.restore()
                break
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.getChildAdapterPosition(view) == 0) {
            val lp = layout.layoutParams as ViewGroup.MarginLayoutParams
            outRect.set(0, layout.measuredHeight + lp.bottomMargin + lp.topMargin, 0, 0)
        } else {
            outRect.setEmpty()
        }
    }
}

class HeadlineItemDecorator: RecyclerView.ItemDecoration() {

    private var header: Bitmap? = null
    private val paint = Paint()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val params = view.layoutParams as? RecyclerView.LayoutParams
        if (params == null) {
            super.getItemOffsets(outRect, view, parent, state)
        } else {
            val position = params.viewAdapterPosition
            if (position % 5 == 0 && position != 0) { // CUSTOMIZE AQUI O COMPORTAMENTO
                outRect.set(0, 150, 0, 0)
            } else {
                super.getItemOffsets(outRect, view, parent, state)
            }
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        initHeader(parent)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            if (position % 5 == 0 && position != 0) {
                header?.let {
                    c.drawBitmap(it, 0.toFloat(), view.top.toFloat() - 100, paint)
                }
            } else {
                super.onDraw(c, parent, state)
            }
        }
    }

    private fun initHeader(parent: RecyclerView?) {
        if (header == null) {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_decorator_text_divider, parent, false) as TextView
            view.text = "Header Customizado!"
            val bitmap = Bitmap.createBitmap(parent?.width?:0, 100, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val widthSpec = View.MeasureSpec.makeMeasureSpec(parent?.width ?: 0, View.MeasureSpec.EXACTLY)
            val heightSpec = View.MeasureSpec.makeMeasureSpec(150, View.MeasureSpec.EXACTLY)
            view.measure(widthSpec, heightSpec)
            view.layout(0, 0, parent!!.width, parent.height)
            view.draw(canvas)
            header = bitmap
        }
    }
}