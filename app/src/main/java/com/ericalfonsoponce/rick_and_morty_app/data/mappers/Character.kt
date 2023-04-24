package com.ericalfonsoponce.rick_and_morty_app.data.mappers

import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.CharacterLocal
import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.CharacterRemote
import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.location.LocationLocal
import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.origin.OriginLocal
import com.ericalfonsoponce.rick_and_morty_app.domain.model.Character
import com.ericalfonsoponce.rick_and_morty_app.domain.model.CharacterResult

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

fun CharacterRemote.toLocal() =
    CharacterLocal(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        origin = this.origin?.toLocal(),
        location = this.location?.toLocal(),
        image = this.image,
        url = this.url,
        created = this.created
    )

fun CharacterLocal.toDomain() =
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


fun Character.toLocal() =
    CharacterLocal(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        gender = this.gender,
        origin = OriginLocal(originName = this.origin ?: ""),
        location = LocationLocal(locationName = this.location ?: ""),
        image = this.image
    )

fun List<CharacterLocal>.toDomain() = this.map { it.toDomain() }
fun List<CharacterLocal>.toCharacterResult() =
    CharacterResult(
        null,
        this.toDomain()
    )










