package com.kotlin.login.network

import com.kotlin.login.models.PokemonSimple
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): PokemonSimple
}
