package com.erhanonal.pokekmp.features.pokemon.data.repository

import com.erhanonal.pokekmp.features.pokemon.domain.model.BaseResult
import com.erhanonal.pokekmp.features.pokemon.domain.model.PokemonModel
import com.erhanonal.pokekmp.features.pokemon.domain.repository.PokemonRepository
import com.erhanonal.pokekmp.features.pokemon.domain.usecase.PokemonListError

class PokemonRepositoryImpl : PokemonRepository {
    override suspend fun getPokemonList(): BaseResult<List<PokemonModel>, PokemonListError> {
        return BaseResult.Success(
            listOf(
                PokemonModel(id = "placeholder_id")
            )
        )
    }
}