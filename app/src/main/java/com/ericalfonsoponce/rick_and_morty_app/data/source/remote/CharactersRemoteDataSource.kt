package com.ericalfonsoponce.rick_and_morty_app.data.source.remote

import com.ericalfonsoponce.rick_and_morty_app.data.entity.CharacterResultRemote
import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.CharacterRemote

interface CharactersRemoteDataSource {

    suspend fun getAllCharacters(page: Int): Result<CharacterResultRemote?>
    suspend fun getCharacterDetails(characterId: Int): Result<CharacterRemote?>
}