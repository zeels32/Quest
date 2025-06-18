package com.quests.demo.presentation.ui.home.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quests.demo.events.presentation.ui.events.components.TitleBar

@Composable
fun HomeScreen(
    navigateToEvent: () -> Unit,
    navigateToProduct: () -> Unit
) {


    Scaffold(
        topBar = {
            TitleBar(
                text = "Quests",
                onBack = {}
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp) // Add padding for better spacing,
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = navigateToEvent
                ) {
                    Text(text = "Quest 1")
                }

                Button(
                    onClick = navigateToProduct,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Quest 2")
                }
            }
        }
    )

}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen({}, {})
}