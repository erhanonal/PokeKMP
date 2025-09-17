package com.erhanonal.pokekmp.features.pokemon.presentation.pokemonlist

sealed interface PokemonListEvent {
    data class NavigateToPokemonDetail(val pokemonName: String) : PokemonListEvent
}