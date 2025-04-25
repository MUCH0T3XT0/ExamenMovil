package com.app.examenmovil.presentation.screens.home

import com.app.examenmovil.domain.model.Sudoku

data class HomeUiState(
    val sudoku: Sudoku =
        Sudoku(
            puzzle = emptyList(),
            solution = emptyList(),
        ),
    val isLoading: Boolean = false,
    val error: String? = null,
)
