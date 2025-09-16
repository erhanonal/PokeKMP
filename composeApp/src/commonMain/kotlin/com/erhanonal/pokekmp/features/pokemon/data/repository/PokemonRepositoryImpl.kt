package com.erhanonal.pokekmp.features.pokemon.data.repository

import com.erhanonal.pokekmp.features.pokemon.data.model.PokemonListResponseModel
import com.erhanonal.pokekmp.features.pokemon.data.model.PokemonNetworkModel
import com.erhanonal.pokekmp.features.pokemon.data.service.PokemonService
import com.erhanonal.pokekmp.common.model.BaseResult
import com.erhanonal.pokekmp.features.pokemon.domain.model.PokemonModel
import com.erhanonal.pokekmp.features.pokemon.domain.repository.PokemonRepository
import com.erhanonal.pokekmp.features.pokemon.domain.usecase.PokemonListError
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

internal class PokemonRepositoryImpl(private val service: PokemonService) : PokemonRepository {

    override suspend fun getPokemonList(): BaseResult<List<PokemonModel>, PokemonListError> {
        return runCatching {
            service.getPokemonList()
        }.fold(
            onSuccess = { response -> response.toResult() },
            onFailure = { BaseResult.Error(PokemonListError.UnknownError) }
        )
    }

    private suspend fun HttpResponse.toResult(): BaseResult<List<PokemonModel>, PokemonListError> {
        return if (status.isSuccess()) {
            try {
                val responseBody: PokemonListResponseModel = body()
                val domainModels = responseBody.results.map { it.toDomainModel() }
                BaseResult.Success(domainModels)
            } catch (e: Exception) {
                BaseResult.Error(PokemonListError.UnknownError)
            }
        } else {
            BaseResult.Error(PokemonListError.UnknownError)
        }
    }

    private fun PokemonNetworkModel.toDomainModel(): PokemonModel {
        return PokemonModel(name = name)
    }
}