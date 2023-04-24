package com.ericalfonsoponce.rick_and_morty_app.data.entity.character.location

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LocationLocal(

    @PrimaryKey
    @ColumnInfo(name = "character_location_name")
    var locationName: String = "",

    @ColumnInfo(name = "character_location_url")
    var locationUrl: String? = null
)