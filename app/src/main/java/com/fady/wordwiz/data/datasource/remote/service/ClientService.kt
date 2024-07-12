package com.fady.wordwiz.data.datasource.remote.service

import com.fady.wordwiz.data.dto.DefinitionsDto
import com.fady.wordwiz.data.utils.WORD_QUERY
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientService {

    @GET("{word_query}")
    suspend fun getDefinitions(
        @Path(WORD_QUERY) apiKey: String
    ): DefinitionsDto
}