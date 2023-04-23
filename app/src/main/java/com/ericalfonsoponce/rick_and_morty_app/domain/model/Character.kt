package com.ericalfonsoponce.rick_and_morty_app.domain.model

import java.io.Serializable

class Character(
    val id: Int? = null,
    var name: String? = null,
    var status: String? = null,
    var species: String? = null,
    var gender: String? = null,
    var origin: String? = null,
    var location: String? = null,
    var image: String? = null
) : Serializable