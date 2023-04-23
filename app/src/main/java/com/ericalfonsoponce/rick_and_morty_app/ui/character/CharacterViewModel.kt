package com.ericalfonsoponce.rick_and_morty_app.ui.character

import android.os.Build
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericalfonsoponce.rick_and_morty_app.data.source.remote.api.ApiError
import com.ericalfonsoponce.rick_and_morty_app.domain.model.Character
import com.ericalfonsoponce.rick_and_morty_app.helpers.Constants
import com.ericalfonsoponce.rick_and_morty_app.helpers.extensions.parseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CharacterDetailUiState(
    val characterDetails: Character = Character(),
    val isLoading: Boolean = false,
    val errorResourceId: Int? = 0
)

@HiltViewModel
class CharacterViewModel @Inject constructor(): ViewModel() {

     val uiState by lazy {
        MutableStateFlow(CharacterDetailUiState(isLoading = true))
    }

    fun checkIntent(bundle: Bundle?){
        bundle?.let {
            setLoadingState(true)
            viewModelScope.launch {
                val character = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    it.getSerializable(Constants.CHARACTER, Character::class.java)
                else it.getSerializable(Constants.CHARACTER) as? Character
                character?.let {
                    setCharacterDetails(character)
                } ?: run {
                    setCharacterError(ApiError.NotFound().errorMessage)
                }
            }
        } ?: run {
            setCharacterError(ApiError.NotFound().errorMessage)
        }
    }


    private fun setCharacterDetails(characterDetails: Character) {
        uiState.update {
            it.copy(
                characterDetails = characterDetails
            )
        }
    }

    private fun setCharacterError(error: Int) {
        uiState.update {
            it.copy(
                errorResourceId = error
            )
        }
    }


    private fun setLoadingState(isLoading: Boolean) {
        uiState.update {
            it.copy(
                isLoading = isLoading
            )
        }
    }


    fun clearError() {
        uiState.update {
            it.copy(
                errorResourceId = 0
            )
        }
    }
}