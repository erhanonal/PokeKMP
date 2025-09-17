package com.erhanonal.pokekmp.features.pokemon.data.mapper

import com.erhanonal.pokekmp.common.model.BaseResult
import com.erhanonal.pokekmp.features.pokemon.data.model.PokemonListResponseModel
import com.erhanonal.pokekmp.features.pokemon.data.model.PokemonNetworkModel
import com.erhanonal.pokekmp.features.pokemon.domain.model.PokemonModel
import com.erhanonal.pokekmp.features.pokemon.domain.usecase.PokemonListError
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

object PokemonListMapper {

    suspend fun mapHttpResponseToResult(response: HttpResponse): BaseResult<List<PokemonModel>, PokemonListError> {
        return if (response.status.isSuccess()) {
            try {
                val responseBody: PokemonListResponseModel = response.body()
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