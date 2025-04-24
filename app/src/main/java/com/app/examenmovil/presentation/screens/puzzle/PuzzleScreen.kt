package com.app.examenmovil.presentation.screens.puzzle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.examenmovil.domain.model.Sudoku
import com.app.examenmovil.presentation.screens.puzzle.components.SudokuContent

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun PuzzleScreen(
    fila: Int,
    columna: Int,
    dificultad: String,
    onBackClick: (String) -> Unit,
    viewModel: PuzzleViewModel = hiltViewModel(),
) {
    var searchQuery by remember { mutableStateOf("") }
    // Datos mock
    val mockSudoku = remember { Sudoku.getMockData() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(fila, columna, dificultad) {
        viewModel.getPuzzle(fila, columna, dificultad)
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mi Acertijo") },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = { onBackClick("Volver") }) {
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                        )
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding),
        ) {
            /*
            SudokuContent(
                sudoku = mockSudoku,
                isLoading = false,
                error = null,
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
            )
             */
            // Datos reales
            SudokuContent(
                sudoku = uiState.sudoku,
                isLoading = uiState.isLoading,
                error = uiState.error,
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
            )
        }
    }
}
