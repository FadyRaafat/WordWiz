package com.fady.wordwiz.data.datasource.local.worddictionarydb

import androidx.room.TypeConverter
import com.fady.wordwiz.domain.models.WordDefinition
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class TypeConverter {


    @TypeConverter
    fun fromWordDefinitionList(wordDefinitionList: List<WordDefinition>): String {
        return Gson().toJson(wordDefinitionList)
    }

    @TypeConverter
    fun toWordDefinitionList(wordDefinitionString: String): List<WordDefinition> {
        val type = object : TypeToken<List<WordDefinition>>() {}.type
        return Gson().fromJson(wordDefinitionString, type)
    }


}