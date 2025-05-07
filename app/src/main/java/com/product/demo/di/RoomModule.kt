package com.product.demo.di

import android.content.Context
import androidx.room.Room
import com.product.demo.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideYourDatabase(@ApplicationContext app: Context): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "product_db")
            .build()
    }

    @Singleton
    @Provides
    fun provideYourDao(db: AppDatabase) = db.productDao()

}