package com.fady.wordwiz.domain.usacases

import com.fady.wordwiz.data.utils.Resource
import com.fady.wordwiz.domain.models.WordDefinition
import com.fady.wordwiz.domain.repository.DictionaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDefinitionsUseCase @Inject constructor(
    private val dictionaryRepository: DictionaryRepository,
) {
    operator fun invoke(wordQuery: String): Flow<Resource<List<WordDefinition>>> = flow {
         emit(dictionaryRepository.getDefinitions(wordQuery))
    }.flowOn(Dispatchers.IO)
}