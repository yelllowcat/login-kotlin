package com.kotlin.login.data

import com.kotlin.login.models.PokemonSimple
import com.kotlin.login.network.RetrofitInstance

class PokemonRepository {
    suspend fun getPokemon(name: String): PokemonSimple {
        return RetrofitInstance.api.getPokemon(name.lowercase().trim())
    }
}
