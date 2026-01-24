package com.example.unscramble.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class GameUiState(
    val currentScrambledWord: String = "",
    var isGuessedWrong: Boolean = false,
    var score: Int = 0,
    var currentWordCount: Int = 1,
    var isGameOver: Boolean = false
)
