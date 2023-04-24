package com.ericalfonsoponce.rick_and_morty_app.di

import com.ericalfonsoponce.rick_and_morty_app.data.repository.CharactersRepositoryImpl
import com.ericalfonsoponce.rick_and_morty_app.data.source.local.CharactersLocalDataSource
import com.ericalfonsoponce.rick_and_morty_app.data.source.remote.CharactersRemoteDataSource
import com.ericalfonsoponce.rick_and_morty_app.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCharactersRepository(
        charactersRemoteDataSource: CharactersRemoteDataSource,
        charactersLocalDataSource: CharactersLocalDataSource
    ): CharactersRepository = CharactersRepositoryImpl(
        charactersRemoteDataSource,
        charactersLocalDataSource
    )
}