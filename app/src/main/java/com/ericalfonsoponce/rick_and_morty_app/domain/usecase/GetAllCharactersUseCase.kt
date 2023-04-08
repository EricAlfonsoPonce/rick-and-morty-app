package com.ericalfonsoponce.rick_and_morty_app.domain.usecase

import com.ericalfonsoponce.rick_and_morty_app.domain.model.CharacterResult

interface GetAllCharactersUseCase {

    suspend operator fun invoke(page: Int): Result<CharacterResult?>
}