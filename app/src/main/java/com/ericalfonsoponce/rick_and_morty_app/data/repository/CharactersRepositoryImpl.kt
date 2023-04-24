package com.ericalfonsoponce.rick_and_morty_app.data.repository

import com.ericalfonsoponce.rick_and_morty_app.data.mappers.toDomain
import com.ericalfonsoponce.rick_and_morty_app.data.mappers.toLocal
import com.ericalfonsoponce.rick_and_morty_app.data.source.local.CharactersLocalDataSource
import com.ericalfonsoponce.rick_and_morty_app.data.source.remote.CharactersRemoteDataSource
import com.ericalfonsoponce.rick_and_morty_app.data.source.remote.api.ApiError
import com.ericalfonsoponce.rick_and_morty_app.domain.model.Character
import com.ericalfonsoponce.rick_and_morty_app.domain.model.CharacterResult
import com.ericalfonsoponce.rick_and_morty_app.domain.repository.CharactersRepository
import com.ericalfonsoponce.rick_and_morty_app.helpers.extensions.parseException
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource,
    private val charactersLocalDataSource: CharactersLocalDataSource,
) : CharactersRepository {

    override suspend fun getAllCharacters(page: Int): Result<CharacterResult?> {
        val response = charactersRemoteDataSource.getAllCharacters(page)
        return if (response.isSuccess) {
            response.map { it?.toDomain() }
        } else {
            val exception = response.exceptionOrNull()
            Result.failure(exception ?: Exception())
        }
    }

    override suspend fun getCharacterDetails(characterId: Int): Result<Character?> {
        val response = charactersRemoteDataSource.getCharacterDetails(characterId)
        return if (response.isSuccess) {
            response.map {
                it?.toDomain()
            }
        } else {
            val exception = response.exceptionOrNull()
            Result.failure(exception ?: Exception())
        }
    }

    override suspend fun updateCharacter(character: Character): Result<Boolean> {
        return try {
            charactersLocalDataSource.updateCharacterFromDomain(character.toLocal())
        } catch (exception: Exception) {
            Result.failure(exception.parseException())
        }
    }

    override suspend fun deleteCharacter(character: Character): Result<Int?> {
        val response = charactersLocalDataSource.deleteCharacter(character.toLocal())
        return if (response.isSuccess) {
            if (response.getOrNull() == true) {
                character.id?.let {
                    Result.success(it)
                } ?: Result.failure(ApiError.Unknown())
            } else {
                Result.failure(ApiError.Unknown())
            }
        } else {
            response.exceptionOrNull()?.let {
                Result.failure(it)
            } ?: Result.failure(ApiError.Unknown())
        }
    }
}