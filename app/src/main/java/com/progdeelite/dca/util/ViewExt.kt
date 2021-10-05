package com.progdeelite.dca.util

import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.animation.Animation
import androidx.annotation.RequiresApi

fun View.startAnimation(anim: Animation, onEnd: () -> Unit) {
    anim.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationEnd(anim: Animation?) = onEnd()
        override fun onAnimationRepeat(anim: Animation?) = Unit
        override fun onAnimationStart(anim: Animation?) = Unit
    })
    this.startAnimation(anim)
}

fun View.setVisible(show: Boolean) {
    if (show) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

@RequiresApi(Build.VERSION_CODES.R)
        /** ESCONDE O TECLADO */
fun View.showKeyboard(view: View) {
    windowInsetsController?.show(WindowInsets.Type.ime())
    view.requestFocus()
}

@RequiresApi(Build.VERSION_CODES.R)
        /** EXIBE O TECLADO */
fun View.hideKeyboard(view: View) {
    windowInsetsController?.hide(WindowInsets.Type.ime())
    view.clearFocus()
}
