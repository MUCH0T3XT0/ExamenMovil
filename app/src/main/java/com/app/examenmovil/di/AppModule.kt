package com.app.examenmovil.di

import com.app.examenmovil.data.remote.api.SudokuApi
import com.app.examenmovil.data.repository.SudokuRepositoryImpl
import com.app.examenmovil.domain.repository.SudokuRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// di/AppModule.kt
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson =
            GsonBuilder()
                .setLenient() // Esta es la clave para solucionar el error
                .create()

        return Retrofit
            .Builder()
            .baseUrl("https://api.api-ninjas.com/v1/") // Asegúrate de que la URL base sea correcta
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideExampleApi(retrofit: Retrofit): SudokuApi = retrofit.create(SudokuApi::class.java)

    @Provides
    @Singleton
    fun provideExampleRepository(api: SudokuApi): SudokuRepository = SudokuRepositoryImpl(api)
}
