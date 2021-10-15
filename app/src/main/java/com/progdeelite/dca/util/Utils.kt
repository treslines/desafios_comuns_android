package com.progdeelite.dca.util

import android.os.Build

// Singleton in Kotlin - PERGUNTA FREQUENTE DE RECRUTADORES
object EmulatorDetector {

    private const val EMULATOR_BUILD_PROPERTY = "generic"

    // podemos acessar direto o metodo como se fosse "static" em Java
    fun isEmulator(): Boolean = Build.FINGERPRINT.contains(EMULATOR_BUILD_PROPERTY)
}
