package com.app.examenmovil.domain.model

data class Example(
    val score: Double,
    val text: String,
    val sentiment: String,
) {
    companion object {
        fun getMockData(): List<Example> =
            listOf(
                Example(
                    score = -0.10,
                    text = "I hate you",
                    sentiment = "Negative",
                ),
                Example(
                    score = 0.4,
                    text = "You scolded me",
                    sentiment = "Negative",
                ),
            )
    }
}
