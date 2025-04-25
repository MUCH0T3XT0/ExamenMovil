package com.app.examenmovil.presentation.screens.puzzle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.examenmovil.domain.common.Result
import com.app.examenmovil.domain.usecase.GetGeneratePuzzleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PuzzleViewModel
    @Inject
    constructor(
        private val getGeneratePuzzleUseCase: GetGeneratePuzzleUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(PuzzleUiState())
        val uiState: StateFlow<PuzzleUiState> = _uiState.asStateFlow()

        fun getPuzzle(
            columna: Int,
            fila: Int,
            dificultad: String,
        ) {
            viewModelScope.launch {
                getGeneratePuzzleUseCase(columna, fila, dificultad).collect { result ->
                    _uiState.update { state ->
                        when (result) {
                            is Result.Loading -> state.copy(isLoading = true)
                            is Result.Success ->
                                state.copy(
                                    sudoku = result.data,
                                    isLoading = false,
                                    error = null,
                                )

                            is Result.Error ->
                                state.copy(
                                    error = result.exception.message,
                                    isLoading = false,
                                )
                        }
                    }
                }
            }
        }
    }
