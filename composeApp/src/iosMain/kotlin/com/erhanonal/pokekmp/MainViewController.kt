package com.erhanonal.pokekmp

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.window.ComposeUIViewController
import com.erhanonal.pokekmp.app.App

val LocalNativeViewFactory = staticCompositionLocalOf<NativeViewFactory> {
    error("No view factory provided.")
}

fun MainViewController(
    nativeViewFactory: NativeViewFactory
) = ComposeUIViewController() {
    CompositionLocalProvider(
        LocalNativeViewFactory provides nativeViewFactory
    ) {
        App()
    }
}