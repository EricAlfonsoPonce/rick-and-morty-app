package com.ericalfonsoponce.rick_and_morty_app.di

import com.ericalfonsoponce.rick_and_morty_app.domain.repository.CharactersRepository
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.GetAllCharactersUseCase
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.GetCharacterDetailsUseCase
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.impl.GetAllCharactersUserCaseImpl
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.impl.GetCharacterDetailUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesGetAllCharacterUseCase(
        charactersRepository: CharactersRepository
    ): GetAllCharactersUseCase = GetAllCharactersUserCaseImpl(charactersRepository)

    @Provides
    @Singleton
    fun providesGetCharacterDetailsUseCase(
        charactersRepository: CharactersRepository
    ): GetCharacterDetailsUseCase = GetCharacterDetailUseCaseImpl(charactersRepository)

}