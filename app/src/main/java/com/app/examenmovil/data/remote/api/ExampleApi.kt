package com.app.examenmovil.data.remote.api

import com.app.examenmovil.data.remote.dto.ExampleResultDto
import retrofit2.http.GET
import retrofit2.http.Headers

interface ExampleApi {
    @GET("sentiment?text=I%20love%20you")
    @Headers("X-Api-Key: wLVPN1zV08lJYF7uXqgyPw==zVwp6TlVcAO1NLUf")
    suspend fun getExampleList(): ExampleResultDto
}
