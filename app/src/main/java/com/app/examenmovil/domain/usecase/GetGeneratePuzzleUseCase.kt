package com.app.examenmovil.domain.usecase

import com.app.examenmovil.domain.common.Result
import com.app.examenmovil.domain.model.Sudoku
import com.app.examenmovil.domain.repository.ExampleRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetGeneratePuzzleUseCase
    @Inject
    constructor(
        // Inyectado por Hilt
        private val repository: ExampleRepository,
    ) {
        // Puede ser llamado como useCase()
        operator fun invoke(
            columna: Int,
            fila: Int,
            dificultad: String,
        ): Flow<Result<Sudoku>> =
            flow {
                try {
                    // Primer valor: Loading
                    emit(Result.Loading)

                    // Obtiene datos
                    val exampleList = repository.getSudoku(columna, fila, dificultad)

                    // Segundo valor: Success con datos
                    emit(Result.Success(exampleList))
                } catch (e: Exception) {
                    // O Error si algo falla
                    emit(Result.Error(e))
                }
            }
    }
