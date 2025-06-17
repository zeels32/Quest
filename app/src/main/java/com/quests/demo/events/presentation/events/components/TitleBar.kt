package com.quests.demo.events.presentation.events.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TitleBar(
    text: String,
    isBackEnabled: Boolean = false,
    onBack: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        if (isBackEnabled) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize()
                .align(Alignment.Center),
            text = text,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview
@Composable
private fun PreviewTitleBar() {
    TitleBar(
        text = "Events",
        isBackEnabled = true,
        onBack = {}
    )
}