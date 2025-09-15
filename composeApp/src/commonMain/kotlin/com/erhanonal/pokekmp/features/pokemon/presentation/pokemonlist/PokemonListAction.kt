package com.erhanonal.pokekmp.features.pokemon.presentation.pokemonlist

sealed interface PokemonListAction {
    data object FetchData: PokemonListAction
}