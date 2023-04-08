package com.ericalfonsoponce.rick_and_morty_app.domain.usecase.impl

import com.ericalfonsoponce.rick_and_morty_app.domain.model.Character
import com.ericalfonsoponce.rick_and_morty_app.domain.repository.CharactersRepository
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.GetCharacterDetailsUseCase
import javax.inject.Inject

class GetCharacterDetailUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : GetCharacterDetailsUseCase {

    override suspend operator fun invoke(characterId: Int): Result<Character?> {
        return charactersRepository.getCharacterDetails(characterId)
    }
}