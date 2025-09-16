package com.erhanonal.pokekmp.features.pokemon.data.service

import com.erhanonal.pokekmp.common.network.NetworkClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class PokemonService(private val networkClient: NetworkClient) {

    suspend fun getPokemonList(): HttpResponse{
        return networkClient.client.get("pokemon/")
    }

}