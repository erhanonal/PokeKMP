package com.erhanonal.pokekmp.features.pokemon.domain.usecase

import com.erhanonal.pokekmp.common.model.BaseResult
import com.erhanonal.pokekmp.features.pokemon.domain.model.PokemonModel
import com.erhanonal.pokekmp.features.pokemon.domain.repository.PokemonRepository

class GetPokemonListUseCase(
    val repository: PokemonRepository
) {

    suspend operator fun invoke(): BaseResult<List<PokemonModel>, PokemonListError> {
        return repository.getPokemonList()
    }
}

sealed interface PokemonListError {
    data object UnknownError : PokemonListError
}