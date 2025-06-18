package com.quests.demo.events.presentation.ui.events.event_detail.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.quests.demo.events.presentation.ui.events.event.model.EventUiModel
import com.quests.demo.events.presentation.ui.events.event.model.EventUiState

@Composable
fun EventDetailScreen(
    eventDetailViewModel: EventDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {

    Scaffold(
        topBar = {
            TitleBar(
                text = "Event Detail",
                isBackEnabled = true,
                onBack = onBack
            )
        },
        content = { paddingValues ->

            val eventDetailUiState = eventDetailViewModel.eventDetailUiState.collectAsState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                when (eventDetailUiState.value) {
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

                    is EventUiState.Error -> {
                        // Show error message
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Error: ${(eventDetailUiState.value as EventUiState.Error).exception.message}",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    is EventUiState.Success -> {
                        // Show event details
                        val eventUiModel = (eventDetailUiState.value as EventUiState.Success).events.first()
                        EventDetails(eventUiModel = eventUiModel)
                    }
                }

            }
        }
    )

}

@Composable
private fun EventDetails(
    eventUiModel: EventUiModel,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxSize()
    ) {
        Text(
            text = eventUiModel.title,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 5.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = eventUiModel.description,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 5.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}


@Preview
@Composable
private fun PreviewEventDetailScreen() {
    EventDetailScreen(
        onBack = {}
    )
}