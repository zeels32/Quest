package com.quests.demo.events.presentation.events.event_detail.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quests.demo.events.presentation.events.components.TitleBar
import com.quests.demo.events.presentation.events.event.model.EventUiModel

@Composable
fun EventDetailScreen(eventUiModel: EventUiModel, onBack: () -> Unit) {

    Scaffold(
        topBar = {
            TitleBar(
                text = "Event Detail",
                isBackEnabled = true,
                onBack = { onBack() }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                EventDetails(
                    eventUiModel = eventUiModel,
                )
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
        eventUiModel = EventUiModel(
            userId = 1,
            id = 1,
            title = "Sample Event",
            description = "This is a sample event description.",
        ),
        onBack = {}
    )
}