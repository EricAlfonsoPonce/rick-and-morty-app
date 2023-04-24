package com.ericalfonsoponce.rick_and_morty_app.domain.usecase.impl

import com.ericalfonsoponce.rick_and_morty_app.domain.model.Character
import com.ericalfonsoponce.rick_and_morty_app.domain.repository.CharactersRepository
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.DeleteCharacterUseCase
import javax.inject.Inject

class DeleteCharacterUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
): DeleteCharacterUseCase {

    override suspend operator fun invoke(character: Character): Result<Int?> {
        return charactersRepository.deleteCharacter(character)
    }
}