package com.fady.wordwiz.data.repository

import com.fady.wordwiz.data.datasource.local.sharedpref.LocalDataSource
import com.fady.wordwiz.data.datasource.local.worddictionarydb.WordDictionary
import com.fady.wordwiz.data.datasource.local.worddictionarydb.WordDictionaryDao
import com.fady.wordwiz.data.datasource.remote.DictionaryRemoteDataSource
import com.fady.wordwiz.data.utils.FailureStatus
import com.fady.wordwiz.data.utils.Resource
import com.fady.wordwiz.domain.repository.DictionaryRepository
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryRemoteDataSource: DictionaryRemoteDataSource,
    private val wordDictionaryDao: WordDictionaryDao
) : DictionaryRepository {

    override suspend fun getAllWords() = wordDictionaryDao.getAllWords()


    override suspend fun getDefinitions(wordQuery: String) =
        if (LocalDataSource.isConnected) getRemoteDefinitions(wordQuery)
        else getLocalDefinitions(wordQuery)

    private suspend fun getRemoteDefinitions(wordQuery: String) =
        dictionaryRemoteDataSource.getDefinitions(wordQuery).also {
            if (it is Resource.Success) wordDictionaryDao.insertWordDictionary(
                WordDictionary(
                    wordQuery, it.value
                )
            )
        }

    private suspend fun getLocalDefinitions(wordQuery: String) =
        wordDictionaryDao.getWordDictionary(wordQuery)?.wordDefinitions?.let {
            Resource.Success(it)
        } ?: Resource.Failure(FailureStatus.EMPTY, message = "No results found")


}