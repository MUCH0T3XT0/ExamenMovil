package com.app.examenmovil.domain.common

import android.content.Context
import android.content.SharedPreferences
import com.app.examenmovil.domain.model.SavedSudoku
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrefsManager(
    context: Context,
) {
    private val prefs: SharedPreferences = context.getSharedPreferences("SudokuPrefers", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        private const val KEY_PUZZLES = "key_saved_sudoku_puzzles"
    }

    // Guarda una nueva partida al final de la lista
    fun savePuzzle(
        state: List<List<Int?>>,
        puzzle: List<List<Int?>>,
        solution: List<List<Int?>>,
    ): Int {
        val currentList = getAllSavedPuzzles().toMutableList()
        currentList.add(SavedSudoku(state, puzzle, solution))
        val json = gson.toJson(currentList)
        prefs.edit().putString(KEY_PUZZLES, json).apply()
        return currentList.lastIndex // este será el ID
    }

    // Devuelve todas las partidas guardadas
    fun getAllSavedPuzzles(): List<SavedSudoku> {
        val json = prefs.getString(KEY_PUZZLES, null) ?: return emptyList()
        val type = object : TypeToken<List<SavedSudoku>>() {}.type // Cambié aquí el tipo
        return gson.fromJson(json, type)
    }

    // Obtiene una partida por índice (ID)
    fun getPuzzleById(id: Int): SavedSudoku? {
        val list = getAllSavedPuzzles()
        return list.getOrNull(id)
    }

    // Borra todas las partidas
    fun clearAllPuzzles() {
        prefs.edit().remove(KEY_PUZZLES).apply()
    }
}
