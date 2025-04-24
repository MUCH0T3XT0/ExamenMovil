package com.app.examenmovil.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ExampleResultDto(
    @SerializedName("score") val score: Double,
    @SerializedName("text") val text: String,
    @SerializedName("sentiment") val sentiment: String,
)
