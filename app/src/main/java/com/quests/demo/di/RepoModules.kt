package com.quests.demo.di

import com.quests.demo.events.data.repository.EventRepositoryImpl
import com.quests.demo.events.domain.repository.EventRepository
import com.quests.demo.products.data.repository.ProductRepositoryImpl
import com.quests.demo.products.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModules {

    @Singleton
    @Provides
    fun provideProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository {
        return productRepositoryImpl
    }


    @Singleton
    @Provides
    fun provideEventRepository(
        eventRepositoryImpl: EventRepositoryImpl
    ): EventRepository {
        return eventRepositoryImpl
    }

}