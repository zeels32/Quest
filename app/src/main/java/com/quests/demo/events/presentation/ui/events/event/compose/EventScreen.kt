package com.quests.demo.events.presentation.ui.events.event.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.quests.demo.events.presentation.ui.events.components.TitleBar
import com.quests.demo.events.presentation.ui.events.event.EventViewModel
import com.quests.demo.events.presentation.ui.events.event.model.EventUiModel
import com.quests.demo.events.presentation.ui.events.event.model.EventUiState

@Composable
fun EventScreen(
    eventViewModel: EventViewModel = hiltViewModel(),
    onItemClick: (EventUiModel) -> Unit = {},
    onBack: () -> Unit = { } // Added onBack parameter for back navigation
) {

    Scaffold(
        topBar = {
            TitleBar("Events", isBackEnabled = true, onBack = onBack)
        },
        content = { paddingValues ->

            val eventUiState = eventViewModel.eventUiState.collectAsState()


            when (eventUiState.value) {

                is EventUiState.Loading -> {
                    // Show loading indicator
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is EventUiState.Success -> {
                    // Show the list of events
                    EventList(
                        modifier = Modifier.padding(paddingValues),
                        eventList = (eventUiState.value as EventUiState.Success).events,
                        onItemClick = onItemClick
                    )
                }

                is EventUiState.Error -> {
                    // Show error message
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = (eventUiState.value as EventUiState.Error).exception.message.orEmpty(),
                            modifier = Modifier
                                .padding(paddingValues)
                                .align(Alignment.Center),
                        )
                    }
                }

                else -> {}

            }

        }
    )

}

@Composable
fun EventList(
    modifier: Modifier = Modifier,
    eventList: List<EventUiModel>,
    onItemClick: (EventUiModel) -> Unit
) {

    LazyColumn(
        modifier = modifier
    ) {
        items(
            count = eventList.size,
            key = { index -> eventList[index].id } // Assuming EventUi has a unique id
        ) { index ->
            EventCard(
                eventUiModel = eventList[index],
                onItemClick = onItemClick
            )
        }
    }

}

@Composable
fun EventCard(
    eventUiModel: EventUiModel,
    onItemClick: (EventUiModel) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(eventUiModel)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Text(
            text = eventUiModel.title,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = eventUiModel.description,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}


@Preview
@Composable
private fun PreviewEventScreen() {
    EventList(
        eventList = listOf(
            EventUiModel(userId = 1, id = 1, title = "Event 1", description = "Description for Event 1"),
            EventUiModel(userId = 2, id = 2, title = "Event 2", description = "Description for Event 2"),
            EventUiModel(userId = 3, id = 3, title = "Event 3", description = "Description for Event 3")
        ),
        onItemClick = {}
    )
}
