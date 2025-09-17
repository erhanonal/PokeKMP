package com.erhanonal.pokekmp.features.pokemon.presentation.pokemondetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
expect fun PokemonDetailScreen(
    pokemonName: String,
    onBackClick: () -> Unit,
)
