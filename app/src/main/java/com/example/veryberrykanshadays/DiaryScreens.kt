package com.example.veryberrykanshadays
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// -----------------------------------------------------
// SharedPreferences Helper
// -----------------------------------------------------
fun saveDiary(context: Context, text: String) {
    val prefs = context.getSharedPreferences("diary", Context.MODE_PRIVATE)
    val oldList = prefs.getStringSet("items", setOf())?.toMutableSet() ?: mutableSetOf()
    oldList.add(text)
    prefs.edit().putStringSet("items", oldList).apply()
}

fun loadDiary(context: Context): List<String> {
    val prefs = context.getSharedPreferences("diary", Context.MODE_PRIVATE)
    return prefs.getStringSet("items", setOf())?.toList() ?: emptyList()
}

// -----------------------------------------------------
// 1) 오늘 감사한 일 입력 화면
// -----------------------------------------------------
@Composable
fun TodayInputScreen(onSaved: () -> Unit) {
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("오늘 감사한 일은 무엇인가요?", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("예: 오늘 커피가 정말 맛있었어 ☕") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (text.isNotBlank()) {
                    saveDiary(context, text)
                    onSaved()
                }
            }
        ) {
            Text("저장하기")
        }
    }
}

// -----------------------------------------------------
// 2) 메인 리스트 화면
// -----------------------------------------------------
@Composable
fun DiaryListScreen(onClickDiary: (Int) -> Unit) {
    val context = LocalContext.current
    val items = loadDiary(context)

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("감사일기 목록", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            itemsIndexed(items) { index, item ->
                Text(
                    text = "• $item",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .clickable { onClickDiary(index) }
                )
                Divider()
            }
        }
    }
}

// -----------------------------------------------------
// 3) 상세 화면
// -----------------------------------------------------
@Composable
fun DiaryDetailScreen(id: Int, onBack: () -> Unit) {
    val context = LocalContext.current
    val items = loadDiary(context)
    val item = items.getOrNull(id) ?: "내용 없음"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .clickable { onBack() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("감사일기 상세", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(24.dp))
        Text(item, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(24.dp))
        Text("(화면을 클릭하면 돌아갑니다)")
    }
}
