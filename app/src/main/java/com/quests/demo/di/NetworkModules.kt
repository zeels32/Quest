package com.quests.demo.di

import com.quests.demo.BuildConfig
import com.quests.demo.events.data.remote.api.EventService
import com.quests.demo.products.data.remote.api.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModules {


    @Provides
    @Singleton
    @RetrofitEvent
    fun provideEventRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.EVENT_BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @RetrofitProduct
    fun provideProductRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.PRODUCT_BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY // Can be NONE, BASIC, HEADERS, BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideProductApiService(@RetrofitProduct retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }


    @Provides
    @Singleton
    fun provideEventApiService(@RetrofitEvent retrofit: Retrofit): EventService {
        return retrofit.create(EventService::class.java)
    }

}
