package com.erhanonal.pokekmp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class Closeable {
    private var onClose: (() -> Unit)? = null

    internal constructor(onClose: () -> Unit) {
        this.onClose = onClose
    }

    fun close() {
        onClose?.invoke()
        onClose = null
    }
}

fun <T> StateFlow<T>.watch(block: (T) -> Unit): Closeable {
    val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    val job = scope.launch {
        collect { block(it) }
    }
    return Closeable { job.cancel() }
}

// iOS-friendly wrapper function that Swift can call directly
fun <T> observeStateFlow(stateFlow: StateFlow<T>, observer: (T) -> Unit): Closeable {
    return stateFlow.watch(observer)
}
