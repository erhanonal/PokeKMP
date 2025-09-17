package com.erhanonal.pokekmp.features.pokemon.data.repository

import com.erhanonal.pokekmp.common.model.BaseResult
import com.erhanonal.pokekmp.features.pokemon.data.mapper.PokemonDetailMapper
import com.erhanonal.pokekmp.features.pokemon.data.mapper.PokemonListMapper
import com.erhanonal.pokekmp.features.pokemon.data.service.PokemonService
import com.erhanonal.pokekmp.features.pokemon.domain.model.PokemonDetailModel
import com.erhanonal.pokekmp.features.pokemon.domain.model.PokemonModel
import com.erhanonal.pokekmp.features.pokemon.domain.repository.PokemonRepository
import com.erhanonal.pokekmp.features.pokemon.domain.usecase.PokemonDetailError
import com.erhanonal.pokekmp.features.pokemon.domain.usecase.PokemonListError

internal class PokemonRepositoryImpl(private val service: PokemonService) : PokemonRepository {

    override suspend fun getPokemonList(): BaseResult<List<PokemonModel>, PokemonListError> {
        return runCatching {
            service.getPokemonList()
        }.fold(
            onSuccess = { response -> PokemonListMapper.mapHttpResponseToResult(response) },
            onFailure = { BaseResult.Error(PokemonListError.UnknownError) }
        )
    }

    override suspend fun getPokemonDetail(pokemonName: String): BaseResult<PokemonDetailModel, PokemonDetailError> {
        return runCatching {
            service.getPokemonDetails(name = pokemonName)
        }.fold(
            onSuccess = { response -> PokemonDetailMapper.mapHttpResponseToResult(response) },
            onFailure = { BaseResult.Error(PokemonDetailError.UnknownError) }
        )
    }
}