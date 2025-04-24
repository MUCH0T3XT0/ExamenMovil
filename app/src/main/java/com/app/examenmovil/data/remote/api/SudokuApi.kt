package com.app.examenmovil.data.remote.api

import com.app.examenmovil.data.remote.dto.SudokuDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SudokuApi {
    @GET("sudokugenerate")
    suspend fun getSudoku(
        @Query("width") width: Int,
        @Query("height") height: Int,
        @Query("difficulty") difficulty: String,
        @Query("X-Api-Key") apiKey: String,
    ): SudokuDto
}
