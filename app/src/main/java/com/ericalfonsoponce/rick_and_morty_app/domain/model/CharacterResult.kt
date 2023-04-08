package com.ericalfonsoponce.rick_and_morty_app.domain.model


class CharacterResult(
    var info: Pager? = null,
    val results: List<Character>? = null
)