package com.app.examenmovil.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ExampleListDto(
    @SerializedName("results") val results: List<ExampleResultDto>,
)
