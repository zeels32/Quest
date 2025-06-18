package com.quests.demo.events.domain.usecase

import com.quests.demo.events.domain.model.Event
import com.quests.demo.events.domain.repository.EventRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class FetchEventsDetailUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {


    operator fun invoke(id: Int): Flow<Result<Event>> {

        return flow {
            emit(eventRepository.fetchEventDetail(id))
        }

    }

}