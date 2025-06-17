package com.quests.demo.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitEvent

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitProduct
