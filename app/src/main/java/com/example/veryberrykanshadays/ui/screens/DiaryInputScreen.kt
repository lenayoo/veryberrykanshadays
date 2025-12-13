package com.example.veryberrykanshadays.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.example.veryberrykanshadays.ui.viewmodel.DiaryViewModel

@Composable
fun DiaryInputScreen(
    viewModel: DiaryViewModel,
    onSaved: () -> Unit
) {
    var content by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

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
            value = content,
            onValueChange = { content = it },
            placeholder = { Text("예: 오늘 커피가 정말 맛있었어 ☕") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = false)
                .focusRequester(focusRequester),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.saveDiary(content)
                onSaved()
            },
            enabled = content.isNotBlank()
        ) {
            Text("저장하기")
        }
    }
}

