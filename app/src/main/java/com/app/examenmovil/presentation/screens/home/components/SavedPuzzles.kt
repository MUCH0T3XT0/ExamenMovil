package com.app.examenmovil.presentation.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.examenmovil.domain.common.PrefsManager

@Suppress("ktlint:standard:function-naming")
@Composable
fun SavedPuzzles(
    context: android.content.Context,
    onNavigateClick: (Int) -> Unit,
) {
    val prefsManager = PrefsManager(context)

    // Cargamos todas las partidas guardadas
    val savedPuzzles = prefsManager.getAllSavedPuzzles()

    if (savedPuzzles.isNotEmpty()) {
        Text(
            text = "Partidas guardadas",
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp),
        )

        // Mostramos cada partida guardada en una tarjeta
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
        ) {
            items(savedPuzzles.size) { index ->
                val savedPuzzle = savedPuzzles[index]
                Card(
                    modifier = Modifier.padding(8.dp),
                    onClick = {
                        // Navegar a la partida, pasando el ID (índice) y otros parámetros
                        onNavigateClick(index)
                    },
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Sudoku numero ${index + 1}")
                        Text("Tamaño: ${savedPuzzle.puzzle.size} x ${savedPuzzle.puzzle[0].size}")
                        Text("Toca para continuar tu partida")
                    }
                }
            }
        }
    } else {
        Text(
            text = "No hay partidas guardadas",
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp),
        )
    }
}
