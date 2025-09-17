package com.erhanonal.pokekmp.features.pokemon.data.mapper

import com.erhanonal.pokekmp.common.model.BaseResult
import com.erhanonal.pokekmp.features.pokemon.data.model.PokemonDetailResponseModel
import com.erhanonal.pokekmp.features.pokemon.domain.model.PokemonDetailModel
import com.erhanonal.pokekmp.features.pokemon.domain.usecase.PokemonDetailError
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

object PokemonDetailMapper {

    suspend fun mapHttpResponseToResult(response: HttpResponse): BaseResult<PokemonDetailModel, PokemonDetailError> {
        return if (response.status.isSuccess()) {
            try {
                val responseBody: PokemonDetailResponseModel = response.body()
                val domainModel = responseBody.toDomainModel()
                BaseResult.Success(domainModel)
            } catch (e: Exception) {
                BaseResult.Error(PokemonDetailError.UnknownError)
            }
        } else {
            BaseResult.Error(PokemonDetailError.UnknownError)
        }
    }

    private fun PokemonDetailResponseModel.toDomainModel(): PokemonDetailModel {
        return PokemonDetailModel(
            name = name,
            imageUri = sprites.other.officialArtwork.frontDefault,
            types = types.map { typeSlotModel -> typeSlotModel.type.name }
        )
    }
}