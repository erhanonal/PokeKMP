package com.erhanonal.pokekmp.features.pokemon.presentation.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonDetailViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<PokemonDetailUiState> =
        MutableStateFlow(PokemonDetailUiState.Loading)

    val uiState = _uiState.asStateFlow()

    fun handleAction(action: PokemonDetailAction) {
        when (action) {
            is PokemonDetailAction.FetchData -> getPokemonDetail(action.pokemonName)
        }
    }

    private fun getPokemonDetail(pokemonName: String) {
        viewModelScope.launch {
            _uiState.update { PokemonDetailUiState.Loading }

            // For now, we'll just simulate loading and show the name
            // In the future, you can add actual API calls here
            kotlinx.coroutines.delay(500) // Simulate loading

            _uiState.update { PokemonDetailUiState.Success(pokemonName = pokemonName) }
        }
    }
}