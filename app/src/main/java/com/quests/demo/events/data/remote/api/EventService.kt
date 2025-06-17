package com.quests.demo.events.data.remote.api

import com.quests.demo.events.data.remote.model.EventResponse
import retrofit2.Response
import retrofit2.http.GET

interface EventService {

    @GET("posts")
    suspend fun fetchEventsAsync(): Response<EventResponse>

}