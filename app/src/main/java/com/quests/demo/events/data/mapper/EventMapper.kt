package com.quests.demo.events.data.mapper

import com.quests.demo.events.data.remote.model.EventResponse
import com.quests.demo.events.domain.model.Event
import com.quests.demo.events.presentation.events.event.model.EventUiModel


fun EventResponse.toDomainModel(): List<Event> {
    return this.map { eventResponse ->
        Event(
            userId = eventResponse.userId,
            id = eventResponse.id,
            title = eventResponse.title,
            body = eventResponse.body
        )
    }
}

fun List<Event>.toEventUiModel(): List<EventUiModel> {
    return this.map { event ->
        EventUiModel(
            userId = event.userId ?: 0,
            id = event.id ?: 0,
            title = event.title ?: "",
            description = event.body ?: ""
        )
    }
}