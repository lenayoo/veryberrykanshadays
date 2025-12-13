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
    id: Long,
    viewModel: DiaryViewModel,
    onBack: () -> Unit
) {
    LaunchedEffect(id) {
        viewModel.selectDiary(id)
    }

    val diary = viewModel.selectedDiary

    Column(modifier = Modifier.fillMaxSize()) {
        // 커스텀 상단바
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onBack) {
                    Text("←", style = MaterialTheme.typography.titleLarge)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "감사일기 상세",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        val item = diary

        if (item == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.date,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = item.mood,
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
