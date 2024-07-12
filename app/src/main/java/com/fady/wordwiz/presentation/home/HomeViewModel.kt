package com.fady.wordwiz.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fady.wordwiz.data.utils.Resource
import com.fady.wordwiz.domain.usacases.GetAllSearchedWordsUseCase
import com.fady.wordwiz.domain.usacases.GetDefinitionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getDefinitionsUseCase: GetDefinitionsUseCase,
    val getAllSearchedWordsUseCase: GetAllSearchedWordsUseCase

) : ViewModel() {


    private val _itemsState = MutableStateFlow(HomeDataState())
    val itemsState: StateFlow<HomeDataState> = _itemsState.asStateFlow()

    init {
        getAllWords()
    }

    fun OnEvent(homeEvents: HomeEvents) {
        when (homeEvents) {

            is HomeEvents.OnSearch -> {
                getDefinitions(homeEvents.word)
            }

            is HomeEvents.RefreshHistory -> {
                getAllWords()
            }
        }
    }


    private fun getDefinitions(word: String) {
        getDefinitionsUseCase(word).onStart {
            _itemsState.update {
                it.copy(
                    loading = true
                )
            }
        }.onEach { definitions ->
            when (definitions) {
                is Resource.Failure -> {
                    _itemsState.update {
                        it.copy(
                            definitions = emptyList(),
                            loading = false,
                            error = "An unexpected error occurred"
                        )
                    }
                }

                is Resource.Success -> {
                    _itemsState.update {
                        it.copy(
                            definitions = definitions.value, loading = false
                        )
                    }
                }

                else -> {}
            }
        }.launchIn(viewModelScope)

    }

    private fun getAllWords() {
        getAllSearchedWordsUseCase().onEach { words ->
            _itemsState.update {
                it.copy(
                    searchedWords = words.take(5)
                )
            }
        }.launchIn(viewModelScope)
    }

}