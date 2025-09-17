package com.erhanonal.pokekmp.app

import com.erhanonal.pokekmp.common.di.commonModule
import com.erhanonal.pokekmp.features.pokemon.di.pokemonModule

val koinModules = listOf(pokemonModule, commonModule)