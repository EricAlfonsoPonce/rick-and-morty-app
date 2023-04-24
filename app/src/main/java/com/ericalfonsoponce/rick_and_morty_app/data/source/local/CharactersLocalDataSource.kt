package com.ericalfonsoponce.rick_and_morty_app.data.source.local

import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.CharacterLocal

interface CharactersLocalDataSource {
    suspend fun getAllCharacters(): List<CharacterLocal>
    suspend fun getCharacterDetails(characterId: Int): CharacterLocal?
    suspend fun saveCharacter(character: CharacterLocal)
    suspend fun updateCharacter(remoteCharacter: CharacterLocal)
    suspend fun updateCharacterFromDomain(character: CharacterLocal): Result<Boolean>
    suspend fun deleteCharacter(character: CharacterLocal): Result<Boolean?>
}