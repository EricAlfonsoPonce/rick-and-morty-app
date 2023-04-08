package com.ericalfonsoponce.rick_and_morty_app.domain.usecase

import com.ericalfonsoponce.rick_and_morty_app.domain.model.Character

interface GetCharacterDetailsUseCase {

    suspend operator fun invoke(characterId: Int): Result<Character?>
}