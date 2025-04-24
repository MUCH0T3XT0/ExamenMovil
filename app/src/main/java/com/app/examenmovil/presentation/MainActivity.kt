package com.app.examenmovil.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.app.examenmovil.presentation.screens.navigation.Nav
import com.app.examenmovil.presentation.theme.ExamenMovilTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenMovilTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //
                    Nav()
                }
            }
        }
    }
}
