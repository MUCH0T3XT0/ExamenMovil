package com.app.examenmovil.domain.repository

import com.app.examenmovil.domain.model.Example

interface ExampleRepository {
    suspend fun getExampleList(): Example
}
