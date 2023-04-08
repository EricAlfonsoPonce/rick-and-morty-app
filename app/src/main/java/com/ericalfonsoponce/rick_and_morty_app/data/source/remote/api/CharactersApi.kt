package com.ericalfonsoponce.rick_and_morty_app.data.source.remote.api

import com.ericalfonsoponce.rick_and_morty_app.data.entity.CharacterResultRemote
import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.CharacterRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    // GET
    @GET("character/")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Response<CharacterResultRemote>

    @GET("character/{characterId}")
    suspend fun getCharacterDetails(
        @Path("characterId") characterId: Int
    ): Response<CharacterRemote>

}