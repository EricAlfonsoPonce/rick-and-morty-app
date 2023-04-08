package com.ericalfonsoponce.rick_and_morty_app.data.entity.character

import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.location.LocationRemote
import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.origin.OriginRemote

class CharacterRemote(
    val id: Int? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    val origin: OriginRemote? = null,
    val location: LocationRemote? = null,
    val image: String? = null,
    val url: String? = null,
    val created: String? = null
)