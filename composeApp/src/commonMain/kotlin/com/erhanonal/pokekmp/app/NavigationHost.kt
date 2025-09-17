package com.erhanonal.pokekmp.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.erhanonal.pokekmp.features.pokemon.presentation.pokemondetail.navigateToPokemonDetails
import com.erhanonal.pokekmp.features.pokemon.presentation.pokemondetail.pokemonDetailRoute
import com.erhanonal.pokekmp.features.pokemon.presentation.pokemonlist.PokemonListRoute
import com.erhanonal.pokekmp.features.pokemon.presentation.pokemonlist.pokemonListRoute

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = PokemonListRoute
    ) {
        pokemonListRoute(
            onNavigateToPokemonDetail = { pokemonName ->
                navController.navigateToPokemonDetails(pokemonName)
            }
        )
        pokemonDetailRoute(
            onBackClick = { navController.navigateUp() }
        )
    }
}