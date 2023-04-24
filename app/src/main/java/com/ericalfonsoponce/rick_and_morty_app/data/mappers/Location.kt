package com.ericalfonsoponce.rick_and_morty_app.data.mappers

import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.location.LocationLocal
import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.location.LocationRemote

fun LocationRemote.toDomain() = this.name ?: ""

fun LocationRemote.toLocal() =
    LocationLocal(
        locationName = this.name ?: "",
        locationUrl = this.url
    )
fun LocationLocal.toDomain() = this.locationName