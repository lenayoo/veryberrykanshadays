package com.example.veryberrykanshadays.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.veryberrykanshadays.data.DiaryEntity
import com.example.veryberrykanshadays.ui.viewmodel.DiaryViewModel

@Composable
fun DiaryDetailScreen(
    id: Int,
    viewModel: DiaryViewModel,
    onBack: () -> Unit
) {
    var diary by remember { mutableStateOf<DiaryEntity?>(null) }

    LaunchedEffect(id) {
        diary = viewModel.getDiary(id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("감사일기 상세") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("←")
                    }
                })
        }
    ) { innerPadding ->
        val item = diary

        if (item == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.date,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = moodToEmoji(item.mood),
                    style = MaterialTheme.typography.displayMedium
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = item.content,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
