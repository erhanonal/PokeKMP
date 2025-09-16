package com.erhanonal.pokekmp.common.di

import com.erhanonal.pokekmp.common.network.NetworkClient
import org.koin.dsl.module


val commonModule = module {
    single<NetworkClient> { NetworkClient(BASE_URL) }
}

private const val BASE_URL = "https://pokeapi.co/api/v2/"