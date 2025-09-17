package com.erhanonal.pokekmp.features.pokemon.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponseModel(
    @SerialName("name")
    val name: String,
    @SerialName("sprites")
    val sprites: SpritesModel,
    @SerialName("types")
    val types: List<TypeSlotModel>
)

@Serializable
data class SpritesModel(
    @SerialName("other")
    val other: OtherSpritesModel
)

@Serializable
data class OtherSpritesModel(
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtworkModel
)

@Serializable
data class OfficialArtworkModel(
    @SerialName("front_default")
    val frontDefault: String
)

@Serializable
data class TypeSlotModel(
    @SerialName("type")
    val type: TypeModel
)

@Serializable
data class TypeModel(
    @SerialName("name")
    val name: String
)
