package com.app.examenmovil.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun GeneratorButton(onNavigate: (Int, Int, String) -> Unit) {
    var firstNumber by remember { mutableStateOf("") }
    var secondNumber by remember { mutableStateOf("") }
    var keyword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = "Generar nuevo acertijo",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp),
        )
        OutlinedTextField(
            value = firstNumber,
            onValueChange = {
                firstNumber = it
                errorMessage = ""
            },
            label = { Text("Tamaño de filas") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = secondNumber,
            onValueChange = {
                secondNumber = it
                errorMessage = ""
            },
            label = { Text("Tamaño de columnas") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = keyword,
            onValueChange = {
                keyword = it
                errorMessage = ""
            },
            label = { Text("Dificultad (easy, medium o hard)") },
            modifier = Modifier.fillMaxWidth(),
        )
        Button(
            onClick = {
                val num1 = firstNumber.toIntOrNull()
                val num2 = secondNumber.toIntOrNull()
                if (num1 == null || num2 == null) {
                    errorMessage = "Por favor, ingresa números válidos en filas y columnas."
                } else if (keyword.isBlank()) {
                    errorMessage = "Por favor, ingresa una dificultad."
                } else {
                    errorMessage = ""
                    onNavigate(num1, num2, keyword)
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Generar Puzzle")
        }

        // Mostrar mensaje de error
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
