package com.erhanonal.pokekmp.features.pokemon.presentation.pokemonlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erhanonal.pokekmp.common.theme.SpacingValue
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import pokekmp.composeapp.generated.resources.Res
import pokekmp.composeapp.generated.resources.error_message
import pokekmp.composeapp.generated.resources.error_title
import pokekmp.composeapp.generated.resources.loading_pokemon
import pokekmp.composeapp.generated.resources.pokedex_title
import pokekmp.composeapp.generated.resources.tap_to_view_details

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleAction(PokemonListAction.FetchData)
    }

    PokemonListScreenContent(state = state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokemonListScreenContent(state: PokemonListUiState) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.pokedex_title),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (state) {
                is PokemonListUiState.Loading -> PokemonListScreenLoading()
                is PokemonListUiState.Error -> PokemonListScreenError()
                is PokemonListUiState.Success -> PokemonListScreenSuccess(
                    state = state,
                    contentPadding = paddingValues
                )
            }
        }
    }
}

@Composable
private fun PokemonListScreenLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.clip(RoundedCornerShape(SpacingValue.X6)),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = SpacingValue.X2
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(SpacingValue.X8)
            ) {
                Box(
                    modifier = Modifier
                        .size(SpacingValue.X16)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(SpacingValue.X10),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = SpacingValue.X1
                    )
                }
                Spacer(modifier = Modifier.height(SpacingValue.X4))
                Text(
                    text = stringResource(Res.string.loading_pokemon),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun PokemonListScreenError() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(SpacingValue.X6))
                .padding(SpacingValue.X4),
            color = MaterialTheme.colorScheme.errorContainer,
            shadowElevation = SpacingValue.X2
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(SpacingValue.X8)
            ) {
                Text(
                    text = stringResource(Res.string.error_title),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(SpacingValue.X2))
                Text(
                    text = stringResource(Res.string.error_message),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun PokemonListScreenSuccess(
    state: PokemonListUiState.Success,
    contentPadding: PaddingValues
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = SpacingValue.X4),
        contentPadding = contentPadding
    ) {
        items(
            items = state.items,
            key = { model -> model.name }
        ) { model ->
            PokemonListItem(model = model)
        }
    }
}

@Composable
private fun PokemonListItem(model: PokemonUiModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = SpacingValue.X2),
        shape = RoundedCornerShape(SpacingValue.X4),
        elevation = CardDefaults.cardElevation(defaultElevation = SpacingValue.X2),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SpacingValue.X5),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(SpacingValue.X14)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = model.displayName.first().toString().uppercase(),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(SpacingValue.X4))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = model.displayName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(SpacingValue.X1))
                Text(
                    text = stringResource(Res.string.tap_to_view_details),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}