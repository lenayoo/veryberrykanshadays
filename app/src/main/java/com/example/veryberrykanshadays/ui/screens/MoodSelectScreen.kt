package io.github.lenayoo.veryberrykanshadays.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.lenayoo.veryberrykanshadays.ui.viewmodel.DiaryViewModel

@Composable
fun MoodSelectScreen(
    viewModel: DiaryViewModel,
    onNext: () -> Unit,
    onHome: () -> Unit
) {
    val currentMood = viewModel.currentMood
    val moods = listOf("🥰", "🥳", "😆", "🤯", "🥲")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "今日の気分を教えてください",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(48.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                moods.forEach { emoji ->
                    val selected = currentMood == emoji

                    Surface(
                        tonalElevation = if (selected) 8.dp else 2.dp,
                        shape = MaterialTheme.shapes.large,
                        color = if (selected) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier
                            .size(64.dp)
                            .clickable { viewModel.setMood(emoji) }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = emoji,
                                style = MaterialTheme.typography.displaySmall
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = onNext,
                enabled = currentMood.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Text(
                    text = "次へ",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
