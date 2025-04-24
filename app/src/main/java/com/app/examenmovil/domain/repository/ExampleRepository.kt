package com.app.examenmovil.domain.repository

import com.app.examenmovil.domain.model.Sudoku

interface ExampleRepository {
    suspend fun getSudoku(
        columna: Int,
        fila: Int,
        dificultad: String,
    ): Sudoku
}
