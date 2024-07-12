package com.fady.wordwiz.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.fady.wordwiz.R
import com.fady.wordwiz.domain.models.WordDefinition
import com.fady.wordwiz.presentation.theme.dimen_16
import com.fady.wordwiz.presentation.theme.dimen_8

@Composable
fun DefinitionItem(
    wordDefinition: WordDefinition,

    ) {
    Card(
        modifier = Modifier.padding(dimen_8)
    ) {
        Column(
            Modifier.padding(dimen_16)
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = wordDefinition.definition,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )
            }
            Text(text = wordDefinition.example?.let { stringResource(id = R.string.example, it) }
                ?: stringResource(id = R.string.no_example),
                style = MaterialTheme.typography.bodyLarge)

        }
    }
}

@Preview
@Composable
private fun DefinitionItemPrev() {
    DefinitionItem(
        wordDefinition = WordDefinition(
            definition = "A person who is very interested in and enthusiastic about someone or something.",
            example = "She is a music enthusiast."
        )
    )
}




