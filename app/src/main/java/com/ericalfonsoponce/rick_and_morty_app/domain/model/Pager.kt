package com.ericalfonsoponce.rick_and_morty_app.domain.model

class Pager(
    var count: Int? = null,
    var pages: Int? = null,
    var next: String? = null,
    var prev: String? = null
)