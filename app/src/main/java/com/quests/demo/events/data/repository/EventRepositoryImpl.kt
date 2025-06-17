package com.quests.demo.events.data.repository

import com.quests.demo.events.data.mapper.toDomainModel
import com.quests.demo.events.data.remote.api.EventService
import com.quests.demo.events.domain.model.Event
import com.quests.demo.events.domain.repository.EventRepository
import com.quests.demo.presentation.util.exceptions.NoInternetException
import java.net.UnknownHostException
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventService: EventService,
) : EventRepository {


    override suspend fun fetchEvents(): Result<List<Event>> {
        return runCatching {
            val response = eventService.fetchEventsAsync()
            if (response.isSuccessful) {
                val events = response.body()
                Result.success(events?.toDomainModel() ?: emptyList())
            } else {
                Result.failure(Exception("Error fetching events: ${response.message()}"))
            }
        }.getOrElse { exception ->
            when (exception) {
                is UnknownHostException -> {
                    Result.failure(NoInternetException(message = "No Internet Connection"))
                }
                else -> {
                    Result.failure(Exception("An unexpected error occurred"))
                }
            }
        }
    }

}