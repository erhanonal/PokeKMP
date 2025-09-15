package com.erhanonal.pokekmp.features.pokemon.domain.repository

import com.erhanonal.pokekmp.features.pokemon.domain.model.BaseResult
import com.erhanonal.pokekmp.features.pokemon.domain.model.PokemonModel
import com.erhanonal.pokekmp.features.pokemon.domain.usecase.PokemonListError

interface PokemonRepository {

    suspend fun getPokemonList(): BaseResult<List<PokemonModel>, PokemonListError>
}
