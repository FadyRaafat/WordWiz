package com.fady.wordwiz

import com.fady.wordwiz.data.utils.Resource
import com.fady.wordwiz.domain.models.WordDefinition
import com.fady.wordwiz.domain.repository.DictionaryRepository

class FakeDictionaryRepositoryImpl : DictionaryRepository {
    private var networkError = false

    fun setNetworkError(value: Boolean) {
        networkError = value
    }

    override suspend fun getDefinitions(wordQuery: String): Resource<List<WordDefinition>> {
        return if (networkError) {
            Resource.Error("Error")
        } else {
            Resource.Success(listOf(WordDefinition("word", "definition")))
        }
    }

    override suspend fun getAllWords(): List<String> {
        return if (networkError) {
            emptyList()
        } else {
            listOf("word")
        }
    }
}