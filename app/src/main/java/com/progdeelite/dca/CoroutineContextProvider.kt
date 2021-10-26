package com.progdeelite.dca

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider(
    open val ui: CoroutineContext = Dispatchers.Main,
    open val io: CoroutineContext = Dispatchers.IO
)