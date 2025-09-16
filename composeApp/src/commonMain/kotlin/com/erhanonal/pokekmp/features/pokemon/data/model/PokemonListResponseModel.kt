package com.erhanonal.pokekmp.features.pokemon.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponseModel(
    @SerialName("results")
    val results: List<PokemonNetworkModel>
)

@Serializable
data class PokemonNetworkModel(
    @SerialName("name")
    val name: String
)
