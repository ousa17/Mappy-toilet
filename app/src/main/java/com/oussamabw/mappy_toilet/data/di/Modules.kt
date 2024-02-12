package com.oussamabw.mappy_toilet.data.di

import com.oussamabw.mappy_toilet.data.SanisetteRepositoryImpl
import com.oussamabw.mappy_toilet.data.network.SanisetteService
import com.oussamabw.mappy_toilet.domain.SanisetteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {

    @Provides
    @Singleton
    fun provideSanisetteService(): SanisetteService {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://data.ratp.fr/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SanisetteService::class.java)
    }

    @Provides
    fun provideSanisetteRepository(service: SanisetteService): SanisetteRepository {
        return SanisetteRepositoryImpl(service)
    }

    @Singleton
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
