package com.ericalfonsoponce.rick_and_morty_app.domain.usecase

import com.ericalfonsoponce.rick_and_morty_app.domain.model.Character

interface UpdateCharacterUseCase {
    suspend operator fun invoke(character: Character): Result<Boolean>
}