package com.quests.demo.presentation.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.quests.demo.presentation.theme.QuestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuestTheme {
                Scaffold(
                    modifier = Modifier.Companion.fillMaxSize()
                ) { innerPadding ->

                    Column(
                        modifier = Modifier.Companion
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {

                        AppNavHost(navController = rememberNavController())

                    }

                }
            }
        }
    }
}