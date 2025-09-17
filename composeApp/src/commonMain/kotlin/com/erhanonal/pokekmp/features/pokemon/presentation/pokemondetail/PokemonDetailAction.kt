package com.erhanonal.pokekmp.features.pokemon.presentation.pokemondetail

sealed interface PokemonDetailAction {
    data class FetchData(val pokemonName: String) : PokemonDetailAction
}