package com.erhanonal.pokekmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform