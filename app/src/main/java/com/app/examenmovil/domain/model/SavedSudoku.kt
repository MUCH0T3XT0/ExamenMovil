package com.app.examenmovil.domain.model

data class SavedSudoku(
    val state: List<List<Int?>>,
    val puzzle: List<List<Int?>>,
    val solution: List<List<Int?>>,
)
