package com.erhanonal.pokekmp.features.pokemon.di

import com.erhanonal.pokekmp.features.pokemon.data.repository.PokemonRepositoryImpl
import com.erhanonal.pokekmp.features.pokemon.data.service.PokemonService
import com.erhanonal.pokekmp.features.pokemon.domain.repository.PokemonRepository
import com.erhanonal.pokekmp.features.pokemon.domain.usecase.GetPokemonListUseCase
import com.erhanonal.pokekmp.features.pokemon.presentation.pokemondetail.PokemonDetailViewModel
import com.erhanonal.pokekmp.features.pokemon.presentation.pokemonlist.PokemonListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val pokemonModule = module {

    // Data
    single { PokemonService(get()) }
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }

    // Domain
    factory { GetPokemonListUseCase(get()) }

    // Presentation
    viewModel { PokemonListViewModel(get()) }
    viewModel { PokemonDetailViewModel() }
}