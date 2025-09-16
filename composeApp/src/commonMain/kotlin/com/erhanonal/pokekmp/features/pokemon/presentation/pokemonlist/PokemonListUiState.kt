package com.erhanonal.pokekmp.features.pokemon.presentation.pokemonlist

sealed interface PokemonListUiState {
    data object Loading: PokemonListUiState
    data object Error: PokemonListUiState
    data class Success(val items: List<PokemonUiModel>): PokemonListUiState
}

data class PokemonUiModel(
    val name: String,
    val displayName: String
)