package com.app.examenmovil.di

import com.app.examenmovil.data.remote.api.ExampleApi
import com.app.examenmovil.data.repository.ExampleRepositoryImpl
import com.app.examenmovil.domain.repository.ExampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// di/AppModule.kt
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideExampleApi(retrofit: Retrofit): ExampleApi = retrofit.create(ExampleApi::class.java)

    @Provides
    @Singleton
    fun provideExampleRepository(api: ExampleApi): ExampleRepository = ExampleRepositoryImpl(api)
}
