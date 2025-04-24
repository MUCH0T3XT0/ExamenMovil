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
import com.app.examenmovil.domain.model.Sudoku
import com.app.examenmovil.presentation.screens.home.components.GeneratorButton

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    onNavigateClick: (String) -> Unit,
    onNavigateToPuzzle: (Int, Int, String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var searchQuery by remember { mutableStateOf("") }
    // Datos mock
    val mockSudoku = remember { Sudoku.getMockData() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mi Acertijo") },
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
            GeneratorButton(
                onNavigate = { num1, num2, keyword ->
                    // Aquí llama tu función de navegación con los datos
                    onNavigateToPuzzle(num1, num2, keyword) // ejemplo simple
                },
            )
        }
    }
}
