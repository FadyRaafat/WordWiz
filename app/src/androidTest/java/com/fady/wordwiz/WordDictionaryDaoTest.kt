package com.fady.wordwiz

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fady.wordwiz.data.datasource.local.worddictionarydb.AppDataBase
import com.fady.wordwiz.data.datasource.local.worddictionarydb.WordDictionary
import com.fady.wordwiz.data.datasource.local.worddictionarydb.WordDictionaryDao
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WordDictionaryDaoTest {

    private lateinit var database: AppDataBase
    private lateinit var dao: WordDictionaryDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), AppDataBase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getWordDictionaryDao()
    }

    @Test
    fun testInsertItemIsWorking() = runBlocking {
        dao.insertWordDictionary(WordDictionary("test 1", emptyList()))

        val allItems = dao.getWordDictionary("test 1")
        assertThat(allItems).isNotNull()
    }


    @Test
    fun testGetAllItemsIsWorking() = runBlocking {
        dao.insertWordDictionary(WordDictionary("test 1", emptyList()))
        dao.insertWordDictionary(WordDictionary("test 2", emptyList()))
        dao.insertWordDictionary(WordDictionary("test 3", emptyList()))


        val allShoppingItem = dao.getAllWords()
        assertThat(allShoppingItem.size).isEqualTo(3)
    }


    @After
    fun teardown() {
        database.close()
    }


}
