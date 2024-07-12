package com.fady.wordwiz.domain.repository

import com.fady.wordwiz.data.utils.Resource
import com.fady.wordwiz.domain.models.WordDefinition

interface DictionaryRepository {

    suspend fun getDefinitions(wordQuery: String): Resource<List<WordDefinition>>

    suspend fun getAllWords(): List<String>

}