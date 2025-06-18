package com.quests.demo.events.presentation.ui.events.event_detail.compose

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quests.demo.di.QuestIoDispatcher
import com.quests.demo.events.data.mapper.toEventUiModel
import com.quests.demo.events.domain.usecase.FetchEventsDetailUseCase
import com.quests.demo.events.presentation.ui.events.event.model.EventUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val fetchEventsDetailUseCase: FetchEventsDetailUseCase,
    @QuestIoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {


    private val _eventDetailUiState: MutableStateFlow<EventUiState> = MutableStateFlow(EventUiState.Loading)
    val eventDetailUiState: StateFlow<EventUiState> = _eventDetailUiState.asStateFlow()


    init {
        savedStateHandle.get<String>("id")?.let { id ->
            getEventDetailById(id.toInt())
        } ?: run {
            _eventDetailUiState.update { EventUiState.Error(Exception("ID not found")) }
        }
    }

    private fun getEventDetailById(id: Int) = viewModelScope.launch {

        fetchEventsDetailUseCase.invoke(id)
            .flowOn(ioDispatcher)
            .onStart {
                _eventDetailUiState.update { EventUiState.Loading }
            }
            .catch { e ->
                _eventDetailUiState.update { EventUiState.Error(Exception(e.message)) }
            }.collect { eventDetail ->
                _eventDetailUiState.update {
                    eventDetail.getOrNull()?.let {
                        EventUiState.Success(listOf(it.toEventUiModel()))
                    } ?: EventUiState.Error(Exception("Event not found"))
                }
            }


    }

}