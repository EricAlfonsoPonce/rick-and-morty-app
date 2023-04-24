package com.ericalfonsoponce.rick_and_morty_app.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.CharacterLocal
import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.location.LocationLocal
import com.ericalfonsoponce.rick_and_morty_app.data.entity.character.origin.OriginLocal

@Database(
    entities = [CharacterLocal::class, OriginLocal::class, LocationLocal::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}