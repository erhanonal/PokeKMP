package com.erhanonal.pokekmp.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.erhanonal.pokekmp.common.di.commonModule
import com.erhanonal.pokekmp.common.theme.PokeKMPTheme
import com.erhanonal.pokekmp.features.pokemon.di.pokemonModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(pokemonModule, commonModule)
        }
    ) {
        val navController = rememberNavController()

        PokeKMPTheme {
           NavigationHost(navController = navController)
        }
    }
}
