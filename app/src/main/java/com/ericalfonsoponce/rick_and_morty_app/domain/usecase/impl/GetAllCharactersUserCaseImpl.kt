package com.ericalfonsoponce.rick_and_morty_app.domain.usecase.impl

import com.ericalfonsoponce.rick_and_morty_app.domain.model.CharacterResult
import com.ericalfonsoponce.rick_and_morty_app.domain.repository.CharactersRepository
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.GetAllCharactersUseCase
import javax.inject.Inject

class GetAllCharactersUserCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : GetAllCharactersUseCase {

    override suspend operator fun invoke(page: Int): Result<CharacterResult?> {
        return charactersRepository.getAllCharacters(page)
    }
}