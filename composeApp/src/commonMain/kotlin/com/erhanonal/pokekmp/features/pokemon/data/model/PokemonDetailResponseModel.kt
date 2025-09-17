package com.erhanonal.pokekmp.features.pokemon.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponseModel(
    @SerialName("name")
    val name: String,
    @SerialName("sprites")
    val sprites: SpritesModel
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
