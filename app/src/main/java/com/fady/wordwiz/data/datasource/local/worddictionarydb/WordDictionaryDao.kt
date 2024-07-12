package com.fady.wordwiz.data.datasource.local.worddictionarydb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDictionaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordDictionary(wordDictionary: WordDictionary)

    @Query("SELECT * FROM WordDictionary WHERE word = :word")
    suspend fun getWordDictionary(word: String): WordDictionary?

    @Query("SELECT word FROM WordDictionary")
    suspend fun getAllWords(): List<String>

}