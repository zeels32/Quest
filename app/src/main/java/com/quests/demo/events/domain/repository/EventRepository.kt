package com.quests.demo.events.domain.repository

import com.quests.demo.events.domain.model.Event


interface EventRepository {

    suspend fun fetchEvents(): Result<List<Event>>

}