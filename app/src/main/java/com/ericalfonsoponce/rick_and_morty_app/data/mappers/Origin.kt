package com.ericalfonsoponce.rick_and_morty_app.data.mappers

import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.origin.OriginLocal
import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.origin.OriginRemote

fun OriginRemote.toDomain() = this.name ?: ""

fun OriginRemote.toLocal() =
    OriginLocal(
        originName = this.name ?: "",
        originUrl = this.url
    )

fun OriginLocal.toDomain() = this.originName