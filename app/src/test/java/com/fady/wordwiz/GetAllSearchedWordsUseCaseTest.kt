package com.fady.wordwiz

import com.fady.wordwiz.domain.usacases.GetAllSearchedWordsUseCase
import junit.framework.TestCase.fail
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllSearchedWordsUseCaseTest {

    lateinit var fakeDictionaryRepositoryImpl: FakeDictionaryRepositoryImpl

    @Before
    fun setUp() {
        fakeDictionaryRepositoryImpl = FakeDictionaryRepositoryImpl()
    }

    @Test
    fun `get all searched words with success response`() = runBlocking {
        val result = GetAllSearchedWordsUseCase(fakeDictionaryRepositoryImpl).invoke().first()
        when {
            result.isNotEmpty() -> assert(true)
            else -> fail()
        }
    }

    @Test
    fun `get all searched words with fail response`() = runBlocking {
        fakeDictionaryRepositoryImpl.setNetworkError(true)
        val result = GetAllSearchedWordsUseCase(fakeDictionaryRepositoryImpl).invoke().first()
        when {
            result.isEmpty() -> assert(true)
            else -> fail()
        }
    }

}