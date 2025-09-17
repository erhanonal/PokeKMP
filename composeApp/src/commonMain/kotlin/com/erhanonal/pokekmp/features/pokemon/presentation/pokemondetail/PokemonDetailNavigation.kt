package com.erhanonal.pokekmp.features.pokemon.presentation.pokemondetail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailNavigation(
    val pokemonName: String
)

fun NavController.navigateToPokemonDetails(pokemonName: String) {
    this.navigate(PokemonDetailNavigation(pokemonName))
}

fun NavGraphBuilder.pokemonDetailRoute(
    onBackClick: () -> Unit
) {
    composable<PokemonDetailNavigation> {
        val args = it.toRoute<PokemonDetailNavigation>()
        PokemonDetailScreen(
            pokemonName = args.pokemonName,
            onBackClick = onBackClick
        )
    }
}
