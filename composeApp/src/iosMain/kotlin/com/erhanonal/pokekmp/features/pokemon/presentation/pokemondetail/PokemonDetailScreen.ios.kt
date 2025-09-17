package com.erhanonal.pokekmp.features.pokemon.presentation.pokemondetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitViewController
import com.erhanonal.pokekmp.LocalNativeViewFactory

@Composable
actual fun PokemonDetailScreen(pokemonName: String, onBackClick: () -> Unit) {
    val factory = LocalNativeViewFactory.current
    UIKitViewController(
        modifier = Modifier.fillMaxSize(),
        factory = {
            factory.createPokemonDetailScreen(
                pokemonName = pokemonName,
                onBackClick = onBackClick
            )
        }
    )
}