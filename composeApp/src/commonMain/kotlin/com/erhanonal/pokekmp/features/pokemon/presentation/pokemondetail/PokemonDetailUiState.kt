package com.erhanonal.pokekmp.features.pokemon.presentation.pokemondetail

sealed interface PokemonDetailUiState {
    data object Loading : PokemonDetailUiState
    data object Error : PokemonDetailUiState
    data class Success(val pokemonName: String) : PokemonDetailUiState
}