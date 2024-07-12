package com.fady.wordwiz.data.datasource.remote

import com.fady.wordwiz.data.datasource.remote.service.ClientService
import com.fady.wordwiz.data.utils.BaseRemoteDataSource
import javax.inject.Inject

class DictionaryRemoteDataSource @Inject constructor(
    private val apiService: ClientService
) : BaseRemoteDataSource() {

    suspend fun getDefinitions(
        wordQuery: String
    ) = safeApiCall {
        apiService.getDefinitions(wordQuery).mapToDomainModel()
    }
}