package com.ericalfonsoponce.rick_and_morty_app.di

import com.ericalfonsoponce.rick_and_morty_app.data.source.remote.CharactersRemoteDataSource
import com.ericalfonsoponce.rick_and_morty_app.data.source.remote.api.CharactersApi
import com.ericalfonsoponce.rick_and_morty_app.data.source.remote.impl.CharactersRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteCharactersDataSource(
        charactersApi: CharactersApi
    ): CharactersRemoteDataSource = CharactersRemoteDataSourceImpl(charactersApi)
}