package com.example.veryberrykanshadays.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.veryberrykanshadays.ui.DiaryViewModel

@Composable
fun MoodSelectScreen(
    viewModel: DiaryViewModel,
    onNext: () -> Unit
) {
    val uiState = viewModel.uiState
    val moods = listOf("😊", "🙂", "😐", "😟", "😢")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "오늘의 기분을 알려주세요?",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            moods.forEachIndexed { index, emoji ->
                val moodValue = index + 1
                val selected = uiState.selectedMood == moodValue

                Surface(
                    tonalElevation = if (selected) 4.dp else 0.dp,
                    shape = MaterialTheme.shapes.medium,
                    color = if (selected) MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .size(56.dp)
                        .clickable { viewModel.selectMood(moodValue) }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = emoji, style = MaterialTheme.typography.headlineMedium)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onNext,
            enabled = uiState.selectedMood != null
        ) {
            Text("다음")
        }
    }
}
