package com.ericalfonsoponce.rick_and_morty_app.data.entity.character.origin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class OriginLocal(

    @PrimaryKey
    @ColumnInfo(name = "character_origin_name")
    var originName: String = "",

    @ColumnInfo(name = "character_origin_url")
    var originUrl: String? = null
)