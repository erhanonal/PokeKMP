package com.erhanonal.pokekmp.features.pokemon.domain.usecase

import com.erhanonal.pokekmp.common.model.BaseResult
import com.erhanonal.pokekmp.features.pokemon.domain.model.PokemonDetailModel
import com.erhanonal.pokekmp.features.pokemon.domain.model.PokemonModel
import com.erhanonal.pokekmp.features.pokemon.domain.repository.PokemonRepository

class GetPokemonDetailUseCase(
    val repository: PokemonRepository
) {

    suspend operator fun invoke(pokemonName: String): BaseResult<PokemonDetailModel, PokemonDetailError> {
        return repository.getPokemonDetail(pokemonName = pokemonName)
    }
}

sealed interface PokemonDetailError {
    data object UnknownError : PokemonDetailError
}