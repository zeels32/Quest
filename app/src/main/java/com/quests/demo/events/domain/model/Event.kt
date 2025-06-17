package com.quests.demo.events.domain.model

data class Event(
    val userId: Int? = null,
    val id: Int? = null,
    val title: String? = null,
    val body: String? = null
)