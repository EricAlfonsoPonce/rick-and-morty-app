package com.ericalfonsoponce.rick_and_morty_app.data.entity

import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.CharacterRemote
import com.ericalfonsoponce.rick_and_morty_app.domain.model.Pager

class CharacterResultRemote(
    val info: Pager? = null,
    val results: List<CharacterRemote>? = null
)