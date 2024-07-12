package com.fady.wordwiz

import com.fady.wordwiz.data.utils.Resource
import com.fady.wordwiz.domain.usacases.GetDefinitionsUseCase
import junit.framework.TestCase.fail
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetDefinitionsUseCaseTest {

    lateinit var fakeDictionaryRepositoryImpl: FakeDictionaryRepositoryImpl

    @Before
    fun setUp() {
        fakeDictionaryRepositoryImpl = FakeDictionaryRepositoryImpl()
    }

    @Test
    fun `get definitions with success response`() = runBlocking {
        val result = GetDefinitionsUseCase(fakeDictionaryRepositoryImpl).invoke("test").first()
        when (result) {
            is Resource.Success -> assert(result.value.isNotEmpty())
            is Resource.Error -> fail()
            is Resource.Empty -> fail()
            is Resource.Failure -> fail()
            is Resource.Loading -> fail()
        }
    }

    @Test
    fun `get definitions with fail response`() = runBlocking {
        fakeDictionaryRepositoryImpl.setNetworkError(true)
        val result = GetDefinitionsUseCase(fakeDictionaryRepositoryImpl).invoke("test").first()
        when (result) {
            is Resource.Success -> fail()
            is Resource.Error -> assert(true)
            is Resource.Empty -> fail()
            is Resource.Failure -> fail()
            is Resource.Loading -> fail()
        }
    }
}