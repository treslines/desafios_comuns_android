package com.progdeelite.dca.util

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.view.View
import android.view.WindowInsets
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.progdeelite.dca.item_decoration.HeadlineItemDecorator
import com.progdeelite.dca.item_decoration.ItemDecoratorWithText

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

// PRÁTICO PARA USAR APÓS VERIFICACÕES DE ENTRADAS
fun TextInputLayout.shake(onEndAction: () -> Unit = {}) {
    val startX = 0f
    val translationX = 35f
    val bounceDuration = 700L

    ObjectAnimator.ofFloat(
        this,
        "translationX",
        startX,
        translationX,
        startX
    ).apply {
        interpolator = BounceInterpolator()
        duration = bounceDuration
        start()
    }.doOnEnd { onEndAction() }
}

fun EditText.setOnEnterKeyListener(action: () -> Unit = {}) {
    setOnKeyListener { _, keyCode, event ->
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            action()
            return@setOnKeyListener true
        }
        return@setOnKeyListener false
    }
}

@Suppress("DEPRECATION")
fun Button.setupButtonBackgroundColor(@ColorRes colorId: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setBackgroundColor(resources.getColor(colorId, null))
    } else {
        setBackgroundColor(resources.getColor(colorId))
    }
}

@Suppress("DEPRECATION")
fun TextView.setTextColorById(@ColorRes colorId: Int){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setTextColor(resources.getColor(colorId, null))
    } else {
        setTextColor(resources.getColor(colorId))
    }
}

// 1) Como animar troca de idioma
// 2) Como balançar componentes pra cima e pra baixo
fun TextView.animateTranslation(translation: String){
    var index = 0
    val mHandler = Handler(Looper.getMainLooper())
    val characterAdder: Runnable = object : Runnable {
        override fun run() {
            text = translation.subSequence(0, index++)
            if (index <= translation.length) {
                mHandler.postDelayed(this, 10)
            }
        }
    }
    text = ""
    mHandler.removeCallbacks(characterAdder)
    mHandler.postDelayed(characterAdder, 10)
}

fun TextInputLayout.animateTranslation(translation: String){
    var index = 0
    val mHandler = Handler(Looper.getMainLooper())
    val characterAdder: Runnable = object : Runnable {
        override fun run() {
            hint = translation.subSequence(0, index++)
            if (index <= translation.length) {
                mHandler.postDelayed(this, 10)
            }
        }
    }
    hint = ""
    mHandler.removeCallbacks(characterAdder)
    mHandler.postDelayed(characterAdder, 10)
}

fun View.bounceUpAndDown(){
    val startX = 0f
    val translationY = 25f
    val bounceDuration = 450L
    ObjectAnimator.ofFloat(
        this,
        "translationY",
        startX,
        translationY,
        startX
    ).apply {
        interpolator = BounceInterpolator()
        duration = bounceDuration
        start()
    }
}

// USANDO SHAPES - DIVISÓRIA SIMPLES
fun RecyclerView.setSimpleDefaultDivider(@DrawableRes drawableRes: Int) {
    val divider = DividerItemDecoration(this.context,DividerItemDecoration.VERTICAL)
    val drawable = ContextCompat.getDrawable(this.context,drawableRes)
    drawable?.let {
        divider.setDrawable(it)
        addItemDecoration(divider)
    }
}

fun RecyclerView.setCustomHeadlineDivider() = addItemDecoration(HeadlineItemDecorator())

// USANDO LAYOUT DE TEXTO - DIVISÕRIAS CUSTOMIZADAS
fun RecyclerView.attachHeader(str: String) = addItemDecoration(ItemDecoratorWithText(this, str))