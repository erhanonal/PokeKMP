package com.erhanonal.pokekmp.features.pokemon.presentation.pokemonlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erhanonal.pokekmp.common.theme.PokeKMPTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleAction(PokemonListAction.FetchData)
    }

    PokeKMPTheme {
        PokemonListScreenContent(state = state)
    }
}

@Composable
private fun PokemonListScreenContent(state: PokemonListUiState) {
    Scaffold {
        when (state) {
            is PokemonListUiState.Loading -> PokemonListScreenLoading()
            is PokemonListUiState.Error -> PokemonListScreenError()
            is PokemonListUiState.Success -> PokemonListScreenSuccess(state = state)
        }
    }
}

@Composable
private fun PokemonListScreenLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun PokemonListScreenError() {
    Text("This is an error screen")
}

@Composable
private fun PokemonListScreenSuccess(state: PokemonListUiState.Success) {
    LazyColumn {
        items(state.items) { model ->
            Text(text = model.id)
        }
    }
}