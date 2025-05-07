package com.product.demo.di

import com.product.demo.data.repository.ProductRepositoryImpl
import com.product.demo.domain.repository.ProductRepository
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

}