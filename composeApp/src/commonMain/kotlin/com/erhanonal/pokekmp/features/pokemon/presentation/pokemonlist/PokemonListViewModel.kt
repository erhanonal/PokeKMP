package com.erhanonal.pokekmp.features.pokemon.presentation.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erhanonal.pokekmp.common.model.BaseResult
import com.erhanonal.pokekmp.features.pokemon.domain.model.PokemonModel
import com.erhanonal.pokekmp.features.pokemon.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<PokemonListUiState> =
        MutableStateFlow(PokemonListUiState.Loading)

    val uiState = _uiState.asStateFlow()

    private val _events = Channel<PokemonListEvent>()
    val events = _events.receiveAsFlow()

    fun handleAction(action: PokemonListAction) {
        when (action) {
            is PokemonListAction.FetchData -> getPokemonList()
            is PokemonListAction.OnPokemonClick ->
                viewModelScope.launch {
                    _events.send(
                        PokemonListEvent.NavigateToPokemonDetail(
                            action.pokemonName
                        )
                    )
                }
        }
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            _uiState.update { PokemonListUiState.Loading }
            val result = getPokemonListUseCase()
            val newUiState = when (result) {
                is BaseResult.Error -> PokemonListUiState.Error
                is BaseResult.Success -> PokemonListUiState.Success(items = result.data.map { model -> model.toUiModel() })
            }
            _uiState.update { newUiState }
        }
    }

    private fun PokemonModel.toUiModel(): PokemonUiModel {
        return PokemonUiModel(
            name = name,
            displayName = name.replaceFirstChar { it.uppercase() }
        )
    }
}