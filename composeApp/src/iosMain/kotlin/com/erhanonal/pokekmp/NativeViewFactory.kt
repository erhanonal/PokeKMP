package com.erhanonal.pokekmp

import platform.UIKit.UIViewController

interface NativeViewFactory {
    fun createPokemonDetailScreen(
        pokemonName: String,
        onBackClick: () -> Unit
    ): UIViewController
}