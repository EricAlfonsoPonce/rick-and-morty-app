package com.ericalfonsoponce.rick_and_morty_app.data.mappers

import com.ericalfonsoponce.rick_and_morty_app.data.entity.CharacterResultRemote
import com.ericalfonsoponce.rick_and_morty_app.domain.model.CharacterResult

fun CharacterResultRemote.toDomain() =
    CharacterResult(
        info = this.info,
        results = results?.map { it.toDomain() }
    )