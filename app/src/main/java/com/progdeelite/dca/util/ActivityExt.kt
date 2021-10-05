package com.progdeelite.dca.util

import android.app.Activity
import android.view.WindowManager

fun Activity.preventScreenshotsAndRecentAppThumbnails() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_SECURE,
        WindowManager.LayoutParams.FLAG_SECURE
    )
}