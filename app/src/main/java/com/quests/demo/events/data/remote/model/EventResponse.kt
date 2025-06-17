package com.quests.demo.events.data.remote.model

import com.google.gson.annotations.SerializedName


class EventResponse : ArrayList<EventResponseItem>()

data class EventResponseItem(
    @SerializedName("body")
    val body: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("userId")
    val userId: Int? = null
)
