package com.app.examenmovil.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.examenmovil.domain.model.Example
import com.app.examenmovil.presentation.screens.home.components.ExampleListContent

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    onExampleClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var searchQuery by remember { mutableStateOf("") }
    val tabs = listOf("Pokémon List", "Search")
    // Datos mock
    val mockExampleList = remember { Example.getMockData() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Examen movil") },
            )
        },
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding),
        ) {
            // Datos Mock
            /*
            ExampleListContent(
                exampleList = mockExampleList,
                isLoading = false,
                error = null,
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                onExampleClick = onExampleClick,
            )
             */
            // Datos reales
            ExampleListContent(
                exampleList = uiState.exampleList,
                isLoading = uiState.isLoading,
                error = uiState.error,
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                onExampleClick = onExampleClick,
            )
        }
    }
}
