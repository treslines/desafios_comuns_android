package com.progdeelite.dca.util

import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.preventScreenshotsAndRecentAppThumbnails() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_SECURE,
        WindowManager.LayoutParams.FLAG_SECURE
    )
}

fun AppCompatActivity.hideActionBar() = supportActionBar?.apply { hide() }

fun AppCompatActivity.showActionBar() = supportActionBar?.apply { show() }