package com.quests.demo.events.presentation.events.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quests.demo.events.data.mapper.toEventUiModel
import com.quests.demo.events.domain.usecase.FetchEventsUseCase
import com.quests.demo.events.presentation.events.event.model.EventUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val fetchEventsUseCase: FetchEventsUseCase,
) : ViewModel() {


    val eventUiState: StateFlow<EventUiState> = fetchEventsUseCase.invoke()
        .map { events ->
            if (events.isSuccess) {
                val eventList = events.getOrNull()
                if (eventList.isNullOrEmpty()) {
                    EventUiState.Error(Exception("No products available"))
                } else {
                    EventUiState.Success(eventList.toEventUiModel())
                }
            } else if (events.isFailure) {
                val exception = Exception(events.exceptionOrNull()?.message ?: "An unexpected error occurred")
                return@map EventUiState.Error(exception)
            } else {
                EventUiState.Error(Exception("An unexpected error occurred"))
            }

        }
        .onStart {
            emit(EventUiState.Loading)
        }
        .catch { e ->
            val message = if (e is HttpException) {
                "No Internet Connection"
            } else {
                e.message ?: "An unexpected error occurred"
            }
            emit(EventUiState.Error(Exception(message)))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5000),
            initialValue = EventUiState.Loading
        )

}