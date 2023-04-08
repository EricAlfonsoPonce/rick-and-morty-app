package com.ericalfonsoponce.rick_and_morty_app.data.repository

import com.ericalfonsoponce.rick_and_morty_app.data.source.remote.CharactersRemoteDataSource
import com.ericalfonsoponce.rick_and_morty_app.data.source.remote.api.ApiError
import com.ericalfonsoponce.rick_and_morty_app.domain.model.CharacterResult
import com.ericalfonsoponce.rick_and_morty_app.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource
): CharactersRepository {

    override suspend fun getAllCharacters(page: Int): Result<CharacterResult?> {
        val response = charactersRemoteDataSource.getAllCharacters(page)
        return if (response.isSuccess) {
            response.map { it }
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
            if (exception is ApiError.Network) {
                val localResponse = charactersLocalDataSource.getCharacterDetails(characterId)
                localResponse?.toDomain()?.let {
                    Result.success(it)
                } ?: run {
                    Result.failure(ApiError.NotFound())
                }
            } else {
                Result.failure(exception ?: Exception())
            }
        }
    }
}