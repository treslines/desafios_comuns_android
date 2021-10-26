package com.progdeelite.dca

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

// 1) O que é um DefaultExceptionHandler
// 2) Implementação referencia
// 3) Usando na prática

class ClearableCoroutineScope(context: CoroutineContext) : CoroutineScope {

    private var onViewDetachJob = Job()

    // agrupar/combinar coroutines
    override val coroutineContext: CoroutineContext = context + onViewDetachJob

    fun clearScope() {
        onViewDetachJob.cancel()
    }
}