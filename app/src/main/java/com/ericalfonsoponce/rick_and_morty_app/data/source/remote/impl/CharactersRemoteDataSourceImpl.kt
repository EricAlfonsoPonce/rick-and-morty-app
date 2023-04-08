package com.ericalfonsoponce.rick_and_morty_app.data.source.remote.impl

import com.ericalfonsoponce.rick_and_morty_app.data.entity.CharacterResultRemote
import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.CharacterRemote
import com.ericalfonsoponce.rick_and_morty_app.data.source.remote.CharactersRemoteDataSource
import com.ericalfonsoponce.rick_and_morty_app.data.source.remote.api.CharactersApi
import com.ericalfonsoponce.rick_and_morty_app.helpers.extensions.callResult
import javax.inject.Inject

class CharactersRemoteDataSourceImpl @Inject constructor(
    private val charactersApi: CharactersApi
): CharactersRemoteDataSource {
    override suspend fun getAllCharacters(page: Int): Result<CharacterResultRemote?> {
        return callResult { charactersApi.getAllCharacters(page) }
    }

    override suspend fun getCharacterDetails(characterId: Int): Result<CharacterRemote?> {
        return callResult { charactersApi.getCharacterDetails(characterId) }
    }
}