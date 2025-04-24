package com.app.examenmovil.presentation.screens.home

import com.app.examenmovil.domain.model.Example

data class HomeUiState(
    val exampleList: Example = Example(score = 0.0, text = "", sentiment = ""),
    val isLoading: Boolean = false,
    val error: String? = null,
)
