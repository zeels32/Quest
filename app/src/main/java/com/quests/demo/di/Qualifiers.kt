package com.quests.demo.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitEvent

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitProduct


@Retention(AnnotationRetention.BINARY)
@Qualifier
internal annotation class QuestIoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
internal annotation class QuestMainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
internal annotation class QuestDefaultDispatcher
