package com.ericalfonsoponce.rick_and_morty_app.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericalfonsoponce.rick_and_morty_app.domain.model.Character
import com.ericalfonsoponce.rick_and_morty_app.domain.model.Pager
import com.ericalfonsoponce.rick_and_morty_app.domain.usecase.GetAllCharactersUseCase
import com.ericalfonsoponce.rick_and_morty_app.helpers.extensions.parseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class MainUiState(
    val listCharacters: ArrayList<Character> = arrayListOf(),
    val isLoading: Boolean = false,
    val errorResourceId: Int? = 0
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    val uiState by lazy {
        MutableStateFlow(MainUiState(isLoading = true))
    }

    private var page: Int = 1
    private var hasNext: Boolean = true
    private var resetList: Boolean = false

    init {
        getCharacters()

    }
    fun getCharacters() {
        setLoadingState(true)
        viewModelScope.launch {
            getAllCharactersUseCase(page)
                .onFailure {
                    parseFailure(it)
                    endPage()
                }
                .onSuccess { characterResult ->
                    characterResult?.results?.let {
                        setListCharacters(it)
                    }
                    characterResult?.info?.let {
                        setPage(it)
                    }
                }
            setLoadingState(false)
            resetList = false
        }

    }

    private fun setListCharacters(characters: List<Character>) {
        val auxCharactersList: ArrayList<Character> =
            if (resetList) arrayListOf()
            else uiState.value.listCharacters ?: arrayListOf()
        auxCharactersList.addAll(characters)
        uiState.update {
            it.copy(
                listCharacters = auxCharactersList
            )
        }
    }

    fun hasNext(): Boolean = hasNext

    fun refreshData() {
        resetPage()
        resetList = true
        getCharacters()
    }

    private fun endPage() {
        page = 0
    }

    private fun resetPage() {
        page = 1
    }

    private fun setPage(pager: Pager) {
        if (pager.next == null) {
            endPage()
            hasNext = false
        } else {
            page += 1
        }
    }

    private fun parseFailure(throwable: Throwable) {
        val exception = throwable as? Exception
        val apiError = exception?.parseException()
        uiState.update {
            it.copy(
                errorResourceId = apiError?.errorMessage
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