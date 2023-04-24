package com.ericalfonsoponce.rick_and_morty_app.domain.usecase

import com.ericalfonsoponce.rick_and_morty_app.domain.model.Character

interface DeleteCharacterUseCase {
    suspend operator fun invoke(character: Character): Result<Int?>
}