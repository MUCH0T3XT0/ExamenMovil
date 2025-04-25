package com.app.examenmovil.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.examenmovil.presentation.screens.home.HomeScreen
import com.app.examenmovil.presentation.screens.puzzle.PuzzleScreen

sealed class Screen(
    val route: String,
) {
    object Home : Screen("home")

    object Puzzle : Screen("puzzle/{num1}/{num2}/{keyword}") {
        fun createRoute(
            num1: Int,
            num2: Int,
            keyword: String,
        ) = "puzzle/$num1/$num2/$keyword"
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun Nav(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onNavigateClick = {}, // Eliminalo si ya no lo usás
                onNavigateToPuzzle = { num1, num2, keyword ->
                    navController.navigate(Screen.Puzzle.createRoute(num1, num2, keyword))
                },
            )
        }
        composable(
            route = Screen.Puzzle.route,
            arguments =
                listOf(
                    navArgument("num1") { type = NavType.IntType },
                    navArgument("num2") { type = NavType.IntType },
                    navArgument("keyword") { type = NavType.StringType },
                ),
        ) { backStackEntry ->
            val num1 = backStackEntry.arguments?.getInt("num1") ?: 0
            val num2 = backStackEntry.arguments?.getInt("num2") ?: 0
            val keyword = backStackEntry.arguments?.getString("keyword") ?: ""

            PuzzleScreen(
                fila = num1,
                columna = num2,
                dificultad = keyword,
                onBackClick = { navController.popBackStack() },
            )
        }
        composable(
            route = Screen.Puzzle.route,
            arguments =
                listOf(
                    navArgument("num1") { type = NavType.IntType },
                    navArgument("num2") { type = NavType.IntType },
                    navArgument("keyword") { type = NavType.StringType },
                ),
        ) { backStackEntry ->
            val num1 = backStackEntry.arguments?.getInt("num1") ?: 0
            val num2 = backStackEntry.arguments?.getInt("num2") ?: 0
            val keyword = backStackEntry.arguments?.getString("keyword") ?: ""

            PuzzleScreen(
                fila = num1,
                columna = num2,
                dificultad = keyword,
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}
