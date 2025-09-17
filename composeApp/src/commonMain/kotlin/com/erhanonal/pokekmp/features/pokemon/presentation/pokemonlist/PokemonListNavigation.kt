package com.erhanonal.pokekmp.features.pokemon.presentation.pokemonlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object PokemonListRoute

fun NavGraphBuilder.pokemonListRoute(
    onNavigateToPokemonDetail: (String) -> Unit
) {
    composable<PokemonListRoute> {
        PokemonListScreen(
            onNavigateToPokemonDetail = onNavigateToPokemonDetail
        )
    }
}
