package com.fady.wordwiz.domain.usacases

import com.fady.wordwiz.data.utils.Resource
import com.fady.wordwiz.domain.repository.DictionaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllSearchedWordsUseCase @Inject constructor(
    private val dictionaryRepository: DictionaryRepository,
) {
    operator fun invoke( ): Flow<List<String>> = flow {
        emit(dictionaryRepository.getAllWords())
    }.flowOn(Dispatchers.IO)
}