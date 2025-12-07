package com.example.veryberrykanshadays.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.veryberrykanshadays.ui.DiaryViewModel

@Composable
fun DiaryInputScreen(
    viewModel: DiaryViewModel,
    onSaved: () -> Unit
) {
    val uiState = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "오늘 감사한 일은 무엇인가요?",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = uiState.content,
            onValueChange = { viewModel.updateContent(it) },
            placeholder = { Text("예: 오늘 커피가 정말 맛있었어 ☕") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = false),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.saveDiary(onSaved) },
            enabled = uiState.content.isNotBlank()
        ) {
            Text("저장하기")
        }
    }
}

