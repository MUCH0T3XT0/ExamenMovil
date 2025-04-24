package com.app.examenmovil.data.repository

import android.util.Log
import com.app.examenmovil.data.remote.api.SudokuApi
import com.app.examenmovil.domain.model.Sudoku
import com.app.examenmovil.domain.repository.ExampleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SudokuRepositoryImpl
    @Inject
    constructor(
        private val api: SudokuApi,
    ) : ExampleRepository {
        override suspend fun getSudoku(
            columna: Int,
            fila: Int,
            dificultad: String,
        ): Sudoku {
            Log.d("SudokuRepository", "Llamando a API con fila=$fila, columna=$columna, dificultad=$dificultad")

            try {
                val response = api.getSudoku(columna, fila, dificultad, "wLVPN1zV08lJYF7uXqgyPw==zVwp6TlVcAO1NLUf")
                Log.d("SudokuRepository", "La respuesta es: $response")
                return Sudoku(
                    response.puzzle,
                    response.solution,
                )
            } catch (e: retrofit2.HttpException) {
                Log.e("SudokuRepository", "Error HTTP: ${e.code()}, mensaje: ${e.message()}", e)
                throw e
            } catch (e: java.io.IOException) {
                Log.e("SudokuRepository", "Error de red o conexión: ${e.message}", e)
                throw e
            } catch (e: com.google.gson.JsonSyntaxException) {
                Log.e("SudokuRepository", "Error al analizar JSON: ${e.message}", e)
                throw e
            } catch (e: Exception) {
                Log.e("SudokuRepository", "Error inesperado: ${e.message}", e)
                throw e
            }
        }
    }
