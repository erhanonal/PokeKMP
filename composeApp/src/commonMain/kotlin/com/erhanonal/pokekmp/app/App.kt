package com.erhanonal.pokekmp.app

import androidx.compose.runtime.Composable
import com.erhanonal.pokekmp.common.theme.PokeKMPTheme
import com.erhanonal.pokekmp.features.pokemon.di.pokemonModule
import com.erhanonal.pokekmp.features.pokemon.presentation.pokemonlist.PokemonListScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(pokemonModule)
        }
    ) {
        PokeKMPTheme {
            PokemonListScreen()
        }
    }
}