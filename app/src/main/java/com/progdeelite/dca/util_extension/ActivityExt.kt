package com.progdeelite.dca.util_extension

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_VISIBLE
import android.view.WindowManager
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
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

// VEJA VIDEO: NAVEGACÃO MULTIPLA: XXXXXXXXX
const val EXTRA_START_NAV_RES_ID = "EXTRA_START_NAV_RES_ID"
fun <T : AppCompatActivity> Activity.navigateToNavGraph(
    entryPoint: Class<T>,
    @IdRes navGraphStartDestination: Int? = null,
    overridePendingTransition: Boolean = false
) {
    val intent = Intent(this, entryPoint)
    if (navGraphStartDestination != null) {
        intent.putExtra(EXTRA_START_NAV_RES_ID, navGraphStartDestination)
    }
    if (overridePendingTransition) {
        overridePendingTransition(0, 0)
    }
    startActivity(intent)
}

// COMO ESCONDER E EXIBIR O SYSTEM STATUS BAR
fun Activity.hideSystemBars() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        ViewCompat.getWindowInsetsController(window.decorView)?.apply {
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            hide(WindowInsetsCompat.Type.systemBars())
        }
    } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
    }
}

fun Activity.showSystemBars() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        ViewCompat.getWindowInsetsController(window.decorView)?.apply {
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            show(WindowInsetsCompat.Type.systemBars())
        }
    } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_VISIBLE
    }
}