package com.ericalfonsoponce.rick_and_morty_app.domain.repository

import com.ericalfonsoponce.rick_and_morty_app.domain.model.CharacterResult
import com.ericalfonsoponce.rick_and_morty_app.domain.model.Character

interface CharactersRepository {
    suspend fun getAllCharacters(page: Int): Result<CharacterResult?>
    suspend fun getCharacterDetails(characterId: Int): Result<Character?>
}