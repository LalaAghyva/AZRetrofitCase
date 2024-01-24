package com.sirketismi.network.di

import com.google.gson.Gson
import com.sirketismi.network.ApiService
import com.sirketismi.network.CompaignService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataLayerModule {

    @Provides
    @Singleton
    fun provideGson() : Gson {
        return Gson()
    }

    @Provides
    @Singleton
    @FlightAnnotation
    fun provideApiClient(gson: Gson, @FlightAnnotation client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://65a7624794c2c5762da692dd.mockapi.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providerApiService(@FlightAnnotation retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    @FlightAnnotation
    fun provideOkHttpsClient(): OkHttpClient {
        val client = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        client
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

        return client.build()
    }

    @Provides
    @Singleton
    @CampaignAnnotation
    fun provideCampignApiClient(gson: Gson, @CampaignAnnotation client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://65a7624794c2c5762da692dd.mockapi.io/api/v5/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideCampaignService(@CampaignAnnotation retrofit: Retrofit) : CompaignService {
        return retrofit.create(CompaignService::class.java)
    }

    @Provides
    @Singleton
    @CampaignAnnotation
    fun provideCampaignOkHttpsClient(): OkHttpClient {
        val client = OkHttpClient.Builder()

        client
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        return client.build()
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class FlightAnnotation

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class CampaignAnnotation







