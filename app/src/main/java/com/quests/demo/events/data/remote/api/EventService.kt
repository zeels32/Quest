package com.quests.demo.events.data.remote.api

import com.quests.demo.events.data.remote.model.EventResponse
import com.quests.demo.events.data.remote.model.EventResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EventService {

    @GET("posts")
    suspend fun fetchEventsAsync(): Response<EventResponse>

    @GET("posts/{id}")
    suspend fun fetchEventsDetailAsync(
        @Path("id") eventId: Int
    ): Response<EventResponseItem>

}