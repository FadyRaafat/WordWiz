package com.fady.wordwiz.presentation.home

import com.fady.wordwiz.domain.models.WordDefinition


data class HomeDataState(
    val definitions: List<WordDefinition> = emptyList(),
    val searchedWords: List<String> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)
