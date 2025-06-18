package com.quests.demo.events.presentation.ui.events.event.model

sealed interface EventUiState {
    data object Loading : EventUiState
    data class Success(val events: List<EventUiModel>) : EventUiState
    data class Error(val exception: Exception) : EventUiState
}

data class EventUiModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val description: String,
)