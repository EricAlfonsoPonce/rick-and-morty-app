package com.ericalfonsoponce.rick_and_morty_app.data.source.local.database

import androidx.room.*
import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.CharacterLocal

@Dao
interface CharactersDao {
    @Query("SELECT * FROM CharacterLocal")
    fun getAllCharacters(): List<CharacterLocal>

    @Query("SELECT * FROM CharacterLocal WHERE id = :characterId")
    fun getCharacterDetails(characterId: Int): CharacterLocal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterLocal)

    @Update
    fun updateCharacter(character: CharacterLocal)

    @Delete
    fun deleteCharacter(character: CharacterLocal)
}