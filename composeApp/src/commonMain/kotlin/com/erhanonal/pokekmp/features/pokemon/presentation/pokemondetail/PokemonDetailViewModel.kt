package com.erhanonal.pokekmp.features.pokemon.presentation.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erhanonal.pokekmp.common.model.BaseResult
import com.erhanonal.pokekmp.features.pokemon.domain.model.PokemonDetailModel
import com.erhanonal.pokekmp.features.pokemon.domain.model.PokemonModel
import com.erhanonal.pokekmp.features.pokemon.domain.usecase.GetPokemonDetailUseCase
import com.erhanonal.pokekmp.features.pokemon.presentation.pokemonlist.PokemonListUiState
import com.erhanonal.pokekmp.features.pokemon.presentation.pokemonlist.PokemonUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

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

            val result = getPokemonDetailUseCase(pokemonName = pokemonName)
            val newUiState = when (result) {
                is BaseResult.Error -> PokemonDetailUiState.Error
                is BaseResult.Success -> PokemonDetailUiState.Success(model = result.data.toUiModel())
            }
            _uiState.update { newUiState }
        }
    }
}

private fun PokemonDetailModel.toUiModel(): PokemonDetailUiModel {
    return PokemonDetailUiModel(
        name = name,
        displayName = name.replaceFirstChar { it.uppercase() },
        imageUri = imageUri
    )
}
