package com.progdeelite.dca.util

import android.view.WindowManager
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.progdeelite.dca.MainActivity

fun AppCompatActivity.preventScreenshotsAndRecentAppThumbnails() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_SECURE,
        WindowManager.LayoutParams.FLAG_SECURE
    )
}

fun AppCompatActivity.hideActionBar() = supportActionBar?.apply { hide() }

fun AppCompatActivity.showActionBar() = supportActionBar?.apply { show() }

fun AppCompatActivity.setActionBarTitle(@StringRes title:Int) = supportActionBar?.setTitle(title)

fun AppCompatActivity.setActionBarIcon(@DrawableRes icon:Int) = supportActionBar?.setIcon(icon)

// PARA QUE ESSA EXTENSÃO FUNCIONE, VC TEM QUE OBVIAMENTE DEFINIR UMA TOOLBAR NA SUA MAINACTIVITY
fun AppCompatActivity.setToolbarNavigationIcon(@DrawableRes iconId: Int?) {
    (this as MainActivity).setToolbarNavigationIcon(
        iconId?.let { ContextCompat.getDrawable(this, it) }
    )
}

// PARA QUE ESSA EXTENSÃO FUNCIONE, VC TEM QUE OBVIAMENTE DEFINIR UMA TOOLBAR NA SUA MAINACTIVITY
fun AppCompatActivity.setToolbarNavigationTitle(title: String?) {
    (this as MainActivity).setToolbarNavigationTitle(title)
}