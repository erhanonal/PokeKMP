package com.erhanonal.pokekmp.features.pokemon.presentation.pokemondetail

sealed interface PokemonDetailUiState {
    data object Loading : PokemonDetailUiState
    data object Error : PokemonDetailUiState
    data class Success(val model: PokemonDetailUiModel) : PokemonDetailUiState
}

data class PokemonDetailUiModel(
    val name: String,
    val displayName: String,
    val imageUri: String
)
