package com.app.examenmovil.presentation.screens.puzzle.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.examenmovil.domain.common.PrefsManager
import com.app.examenmovil.domain.model.SavedSudoku
import com.app.examenmovil.domain.model.Sudoku

@Suppress("ktlint:standard:function-naming")
@Composable
fun SudokuContent(
    sudoku: Sudoku,
    onBackClick: (String) -> Unit,
    saveSudoku: SavedSudoku? = null, // Parámetro opcional para recibir el SavedSudoku
) {
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    // Usa 'saveSudoku' si está disponible, de lo contrario usa el 'sudoku' recibido
    val puzzleToUse = saveSudoku?.puzzle ?: sudoku.puzzle
    val solutionToUse = saveSudoku?.solution ?: sudoku.solution

    // Estado mutable para el puzzle (permite modificar los valores)
    var puzzleState by remember {
        mutableStateOf(
            puzzleToUse!!.map { it.toMutableList() },
        )
    }
    var showSuccessDialog by remember { mutableStateOf(false) }
    val editableCells =
        remember {
            puzzleToUse!!.map { row ->
                row.map { it == null }
            }
        }
    val cellSize = 48.dp
    val verticalScrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()

    fun updatePuzzleCell(
        row: Int,
        col: Int,
        value: Int?,
    ) {
        puzzleState =
            puzzleState.mapIndexed { r, rowList ->
                rowList
                    .mapIndexed { c, cell ->
                        if (r == row && c == col) value else cell
                    }.toMutableList()
            }
    }

    // Verificar si el puzzle es correcto
    fun checkPuzzle(): Boolean {
        println("Comprobando el puzzle...")
        println(solutionToUse)
        println(puzzleState)
        for (rowIndex in puzzleState.indices) {
            val row = puzzleState[rowIndex]
            for (colIndex in row.indices) {
                val userValue = row[colIndex]
                val solutionValue = solutionToUse[rowIndex][colIndex]
                println("[$rowIndex][$colIndex] -> Usuario: $userValue, Solución: $solutionValue")
                if (userValue != solutionValue) {
                    println("❌ Mismatch en [$rowIndex][$colIndex]")
                    return false
                }
            }
        }
        println("✅ Puzzle resuelto correctamente.")
        return true
    }

    // Mostrar el resultado en el popup
    fun showCheckResult() {
        if (checkPuzzle()) {
            dialogMessage = "¡Felicidades! El puzzle está resuelto correctamente."
        } else {
            dialogMessage = "El puzzle no está resuelto correctamente. Intenta de nuevo."
        }
        showDialog = true
    }

    Box(modifier = Modifier.fillMaxSize()) {
        println("Sudoku recibido:")
        println(sudoku)
        val rowCount = puzzleToUse!!.size
        val colCount = puzzleToUse.firstOrNull()?.size ?: 0
        val cellSize =
            when {
                rowCount <= 4 -> 60.dp
                rowCount <= 9 -> 48.dp
                rowCount <= 16 -> 40.dp
                else -> 32.dp
            }
        val scrollPercent =
            remember {
                derivedStateOf {
                    val max = verticalScrollState.maxValue.toFloat()
                    if (max == 0f) 0f else verticalScrollState.value / max
                }
            }
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(8.dp),
        ) {
            Text(
                text = "Puzzle ${rowCount}x$colCount",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp),
            )
            Box(
                modifier =
                    Modifier
                        .verticalScroll(verticalScrollState)
                        .horizontalScroll(horizontalScrollState)
                        .border(2.dp, Color.Gray),
            ) {
                Column {
                    for (row in 0 until rowCount) {
                        Row {
                            for (col in 0 until colCount) {
                                val value = puzzleState[row][col]
                                val blockColor =
                                    if ((row / 4 + col / 4) % 2 == 0) {
                                        Color(0xFFF0F0F0)
                                    } else {
                                        Color.White
                                    }
                                Box(
                                    modifier =
                                        Modifier
                                            .padding(2.dp)
                                            .size(cellSize)
                                            .background(blockColor),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    OutlinedTextField(
                                        value = value?.toString() ?: "",
                                        onValueChange = { newValue ->
                                            val intValue = newValue.toIntOrNull()
                                            if (intValue != null || newValue.isEmpty()) {
                                                updatePuzzleCell(row, col, if (newValue.isEmpty()) null else intValue)
                                            }
                                        },
                                        enabled = editableCells[row][col],
                                        textStyle =
                                            MaterialTheme.typography.bodyMedium.copy(
                                                fontSize = 14.sp,
                                                textAlign = TextAlign.Center,
                                                color = Color.Black,
                                            ),
                                        modifier = Modifier.fillMaxSize(),
                                    )
                                }
                                Box(
                                    modifier =
                                        Modifier
                                            .padding(end = 2.dp)
                                            .width(4.dp)
                                            .fillMaxHeight(scrollPercent.value)
                                            .background(Color.Gray.copy(alpha = 0.5f)),
                                )
                            }
                        }
                    }
                }
            }

            // Botón para verificar el puzzle
            Button(
                onClick = { showCheckResult() },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally),
            ) {
                Text("Verificar Puzzle")
            }
            // Botón para reiniciar el juego
            Button(
                onClick = {
                    puzzleState = puzzleToUse!!.map { it.toMutableList() } // Reinicia el puzzle
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
            ) {
                Text("Reiniciar Juego")
            }
            val context = LocalContext.current
            val prefsManager = remember { PrefsManager(context) }
            Button(
                onClick = {
                    prefsManager.savePuzzle(puzzleState, puzzleToUse, solutionToUse)
                    showSuccessDialog = true
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
            ) {
                Text("Guardar y volver")
            }
        }
    }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = { Text("Partida guardada") },
            text = { Text("✅ Tu partida fue guardada exitosamente.") },
            confirmButton = {
                TextButton(onClick = {
                    showSuccessDialog = false
                    onBackClick("home") // Regresar al Home
                }) {
                    Text("Volver al inicio")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showSuccessDialog = false
                }) {
                    Text("Aceptar")
                }
            },
        )
    }
}
