package io.github.lenayoo.veryberrykanshadays.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.lenayoo.veryberrykanshadays.ui.viewmodel.DiaryViewModel

@Composable
fun DiaryInputScreen(
    viewModel: DiaryViewModel,
    onSaved: () -> Unit,
    onHome: () -> Unit
) {
    var content by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // ホームボタン
        IconButton(
            onClick = onHome,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "ホームに戻る",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "今日感謝したことは何ですか？",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                placeholder = { Text("例：出社前に走ることができた🏃‍♀️") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp),
                shape = MaterialTheme.shapes.medium
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    viewModel.saveDiary(content)
                    onSaved()
                },
                enabled = content.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Text(
                    text = "保存",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

