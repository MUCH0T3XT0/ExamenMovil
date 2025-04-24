package com.app.examenmovil.presentation.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.examenmovil.domain.model.Example

@Suppress("ktlint:standard:function-naming")
@Composable
fun ExampleListContent(
    exampleList: Example,
    isLoading: Boolean,
    error: String?,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onExampleClick: (String) -> Unit,
) {
    /*val filteredList =
        remember(exampleList, searchQuery) {
            if (searchQuery.isBlank()) {
                exampleList
            } else {
                exampleList.filter { example ->
                    example.score.contains(searchQuery, ignoreCase = true) ||
                        example.text.contains(searchQuery, ignoreCase = true)
                }
            }
        }
     */
    var showDialog by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            error != null -> {
                Text(
                    text = error,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error,
                )
            }
            else -> {
                Column {
                    // 🔍 Search input
                    SearchBar(
                        query = searchQuery,
                        onQueryChange = onSearchQueryChange,
                    )

                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .clickable { showDialog = true },
                    ) {
                        Text(
                            text = "${exampleList.score} - ${exampleList.text}",
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }
            }
        }
    }
}
