package com.fady.wordwiz.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fady.wordwiz.R
import com.fady.wordwiz.presentation.component.DefinitionItem
import com.fady.wordwiz.presentation.component.EmptyScreen
import com.fady.wordwiz.presentation.component.LoadingDialog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeScreen(
    state: StateFlow<HomeDataState>, onEvent: (HomeEvents) -> Unit
) {

    val homeState = state.collectAsStateWithLifecycle().value
    var searchText by remember { mutableStateOf("") }
    val historyWords = homeState.searchedWords
    var filteredSuggestions by remember { mutableStateOf(listOf<String>()) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoadingDialog(homeState.loading)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Column {
                    TextField(value = searchText,
                        onValueChange = { newValue ->
                            searchText = newValue
                            filteredSuggestions = historyWords.filter { suggestion ->
                                suggestion.contains(searchText, ignoreCase = true)
                            }
                            expanded = filteredSuggestions.isNotEmpty()
                        },
                        label = { Text(stringResource(id = R.string.search)) },
                        modifier = Modifier.fillMaxSize(),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                IconButton(
                    onClick = {
                        onEvent(HomeEvents.OnSearch(searchText))

                    }, modifier = Modifier.size(56.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            }
        }
        if (expanded) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(8.dp),
                shadowElevation = 8.dp,
                tonalElevation = 8.dp
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)

                ) {
                    itemsIndexed(filteredSuggestions) { _, suggestion ->
                        Text(text = suggestion, modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                searchText = suggestion
                                onEvent(HomeEvents.OnSearch(searchText))
                                expanded = false
                            }
                            .padding(8.dp))
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        when {
            homeState.definitions.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemsIndexed(homeState.definitions) { _, result ->
                        DefinitionItem(wordDefinition = result)
                    }
                }
                onEvent(HomeEvents.RefreshHistory)
            }

            homeState.error != null -> {
                EmptyScreen()
                onEvent(HomeEvents.RefreshHistory)
            }

            else -> {
                EmptyScreen(
                    text = stringResource(id = R.string.empty_search)
                )
            }
        }

    }

}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(state = MutableStateFlow(HomeDataState()), onEvent = {})
}

