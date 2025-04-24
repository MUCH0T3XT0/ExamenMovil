package com.app.examenmovil.presentation.screens.puzzle

import com.app.examenmovil.domain.model.Sudoku

data class PuzzleUiState(
    val sudoku: Sudoku =
        Sudoku(
            puzzle = emptyList(),
            solution = emptyList(),
        ),
    val isLoading: Boolean = false,
    val error: String? = null,
)
