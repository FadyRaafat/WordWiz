package com.fady.wordwiz.data.datasource.local.worddictionarydb

import androidx.room.Entity
import com.fady.wordwiz.domain.models.WordDefinition

@Entity(primaryKeys = ["word"])
data class WordDictionary(
    val word: String, val wordDefinitions: List<WordDefinition>
)
