package com.ericalfonsoponce.rick_and_morty_app.domain.usecase.impl

import com.ericalfonsoponce.rick_and_morty_app.domain.model.Character
import com.ericalfonsoponce.rick_and_morty_app.domain.repository.CharactersRepository
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.UpdateCharacterUseCase
import javax.inject.Inject

class UpdateCharacterUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
): UpdateCharacterUseCase {

    override suspend operator fun invoke(character: Character): Result<Boolean> {
        return charactersRepository.updateCharacter(character)
    }
}