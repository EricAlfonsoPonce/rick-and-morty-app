package com.ericalfonsoponce.rick_and_morty_app.di

import com.ericalfonsoponce.rick_and_morty_app.domain.repository.CharactersRepository
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.DeleteCharacterUseCase
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.GetAllCharactersUseCase
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.GetCharacterDetailsUseCase
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.UpdateCharacterUseCase
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.impl.DeleteCharacterUseCaseImpl
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.impl.GetAllCharactersUserCaseImpl
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.impl.GetCharacterDetailUseCaseImpl
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.impl.UpdateCharacterUseCaseImpl
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

    @Provides
    @Singleton
    fun providesUpdateCharacterUseCase(
        charactersRepository: CharactersRepository
    ): UpdateCharacterUseCase =
        UpdateCharacterUseCaseImpl(charactersRepository)

    @Provides
    @Singleton
    fun providesDeleteCharacterUseCase(
        charactersRepository: CharactersRepository
    ): DeleteCharacterUseCase =
        DeleteCharacterUseCaseImpl(charactersRepository)
}