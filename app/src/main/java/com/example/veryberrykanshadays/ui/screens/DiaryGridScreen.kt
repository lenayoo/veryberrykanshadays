package com.example.veryberrykanshadays.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.veryberrykanshadays.data.DiaryEntity
import com.example.veryberrykanshadays.ui.viewmodel.DiaryViewModel

@Composable
fun DiaryGridScreen(
    viewModel: DiaryViewModel,
    onClickDiary: (Int) -> Unit
) {
    val diaries by viewModel.diaries.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Kansha Diary",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(diaries) { diary ->
                DiaryGridItem(
                    diary = diary,
                    onClick = { onClickDiary(diary.id) }
                )
            }
        }
    }
}

@Composable
private fun DiaryGridItem(
    diary: DiaryEntity,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = diary.date,
                style = MaterialTheme.typography.bodyMedium
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text(
                    text = moodToEmoji(diary.mood),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}

fun moodToEmoji(mood: Int): String =
    when (mood) {
        1 -> "😊"
        2 -> "🙂"
        3 -> "😐"
        4 -> "😟"
        5 -> "😢"
        else -> "😊"
    }

