package com.fady.wordwiz.data.dto

import com.fady.wordwiz.domain.models.WordDefinition
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


class DefinitionsDto : ArrayList<DefinitionsDto.DefinitionsDtoItem>() {
    @Serializable
    data class DefinitionsDtoItem(
        @SerializedName("meanings") val meanings: List<Meaning?>?,
    ) {
        @Serializable
        data class Meaning(
            @SerializedName("definitions") val definitions: List<Definition?>?,
        ) {
            @Serializable
            data class Definition(
                @SerializedName("definition") val definition: String?,
                @SerializedName("example") val example: String?,
            )
        }
    }

    fun mapToDomainModel(): List<WordDefinition> {
        return this.asSequence()
            .flatMap { it.meanings.orEmpty().asSequence() }
            .flatMap { it?.definitions.orEmpty().asSequence() }
            .mapNotNull {
                it?.let { definition ->
                    WordDefinition(
                        definition = definition.definition.orEmpty(),
                        example = definition.example
                    )
                }
            }.toList()
    }
}