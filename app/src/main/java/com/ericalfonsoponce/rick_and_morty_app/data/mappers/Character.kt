package com.ericalfonsoponce.rick_and_morty_app.data.mappers

import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.CharacterRemote
import com.ericalfonsoponce.rick_and_morty_app.domain.model.Character

fun CharacterRemote.toDomain() =
    Character(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        gender = this.gender,
        origin = this.origin?.toDomain(),
        location = this.location?.toDomain(),
        image = this.image
    )
