package com.app.examenmovil.domain.model

data class Sudoku(
    val puzzle: List<List<Int?>>,
    val solution: List<List<Int>>,
) {
    companion object {
        fun getMockData(): Sudoku {
            val puzzle =
                listOf(
                    listOf(10, 11, 12, null, 13, 14, 16, null, null, 1, null, 6, 4, 8, null, 9),
                    listOf(4, 15, 16, 14, 1, 2, null, null, null, null, 8, 10, 3, null, 13, 6),
                    listOf(null, null, null, 8, 10, 15, 7, null, 13, 3, 4, 14, 11, 12, 16, 1),
                    listOf(3, 1, 9, 13, null, 5, null, 6, 15, null, 16, 12, null, null, 10, null),
                    listOf(13, 14, 7, 16, null, 8, 4, null, 12, 6, 15, 3, 10, 9, 11, 2),
                )

            val solution =
                listOf(
                    listOf(10, 11, 12, 7, 13, 14, 16, 3, 5, 1, 2, 6, 4, 8, 15, 9),
                    listOf(4, 15, 16, 14, 1, 2, 11, 12, 9, 7, 8, 10, 3, 5, 13, 6),
                    listOf(2, 5, 6, 8, 10, 15, 7, 9, 13, 3, 4, 14, 11, 12, 16, 1),
                    listOf(3, 1, 9, 13, 4, 5, 8, 6, 15, 11, 16, 12, 7, 2, 10, 14),
                    listOf(13, 14, 7, 16, 5, 8, 4, 1, 12, 6, 15, 3, 10, 9, 11, 2),
                )

            return Sudoku(puzzle = puzzle, solution = solution)
        }
    }
}
