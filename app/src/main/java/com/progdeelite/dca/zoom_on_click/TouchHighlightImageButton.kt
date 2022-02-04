package com.progdeelite.dca.zoom_on_click

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet


/**
 * An image button that uses a blue highlight (@link android.R.attr.selectableItemBackground} to
 * indicate pressed and focused states.
 */
class TouchHighlightImageButton : androidx.appcompat.widget.AppCompatImageButton {
    /**
     * The highlight drawable. This generally a [android.graphics.drawable.StateListDrawable]
     * that's transparent in the default state, and contains a semi-transparent overlay
     * for the focused and pressed states.
     */
    private var mForegroundDrawable: Drawable? = null

    /**
     * The cached bounds of the view.
     */
    private val mCachedBounds = Rect()

    constructor(context: Context) : super(context) { init() }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) { init() }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int) : super(context, attrs, defStyle) { init() }

    private fun init() {
        // Reset default ImageButton background and padding.
        setBackgroundColor(0)
        setPadding(0, 0, 0, 0)

        // Retrieve the drawable resource assigned to the android.R.attr.selectableItemBackground
        // theme attribute from the current theme.
        val a = context.obtainStyledAttributes(intArrayOf(android.R.attr.selectableItemBackground))
        mForegroundDrawable = a.getDrawable(0)
        mForegroundDrawable?.callback = this
        a.recycle()
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()

        // Update the state of the highlight drawable to match
        // the state of the button.
        if (mForegroundDrawable?.isStateful == true) {
            mForegroundDrawable?.state = drawableState
        }

        // Trigger a redraw.
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        // First draw the image.
        super.onDraw(canvas)

        // Then draw the highlight on top of it. If the button is neither focused
        // nor pressed, the drawable will be transparent, so just the image
        // will be drawn.
        mForegroundDrawable?.bounds = mCachedBounds
        mForegroundDrawable?.draw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Cache the view bounds.
        mCachedBounds[0, 0, w] = h
    }
}