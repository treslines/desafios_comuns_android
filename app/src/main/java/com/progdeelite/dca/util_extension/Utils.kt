package com.progdeelite.dca.util

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.progdeelite.dca.R

// Singleton in Kotlin - PERGUNTA FREQUENTE DE RECRUTADORES
object EmulatorDetector {

    private const val EMULATOR_BUILD_PROPERTY = "generic"

    // podemos acessar direto o metodo como se fosse "static" em Java
    fun isEmulator(): Boolean = Build.FINGERPRINT.contains(EMULATOR_BUILD_PROPERTY)
}

@Suppress("DEPRECATION")
fun Drawable.setColor(context: Context, @ColorRes color: Int){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val resolvedColor = ContextCompat.getColor(context, color)
        colorFilter = BlendModeColorFilter(resolvedColor, BlendMode.SRC_ATOP)
    } else {
        setColorFilter(ContextCompat.getColor(context,color),PorterDuff.Mode.SRC_ATOP)
    }
}