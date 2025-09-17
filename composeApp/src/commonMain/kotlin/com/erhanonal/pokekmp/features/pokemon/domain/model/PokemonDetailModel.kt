package com.erhanonal.pokekmp.features.pokemon.domain.model

data class PokemonDetailModel(
    val name: String,
    val imageUri: String,
    val types: List<String>
)