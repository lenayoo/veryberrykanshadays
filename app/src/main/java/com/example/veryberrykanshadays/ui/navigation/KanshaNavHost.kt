package com.example.veryberrykanshadays.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.veryberrykanshadays.ui.screens.DiaryDetailScreen
import com.example.veryberrykanshadays.ui.screens.DiaryGridScreen
import com.example.veryberrykanshadays.ui.screens.DiaryInputScreen
import com.example.veryberrykanshadays.ui.screens.MoodSelectScreen
import com.example.veryberrykanshadays.ui.viewmodel.DiaryViewModel

@Composable
fun KanshaNavHost(
    viewModel: DiaryViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "mood_select"
    ) {
        // 1. 오늘 기분 선택 화면
        composable("mood_select") {
            MoodSelectScreen(
                onMoodSelected = { mood ->
                    viewModel.setMood(mood)          // ViewModel에 기분 저장
                    navController.navigate("input")  // 감사 내용 입력 화면으로
                }
            )
        }

        // 2. 감사 일기 입력 화면
        composable("input") {
            DiaryInputScreen(
                onSave = { text ->
                    viewModel.saveDiary(text)        // Room 저장
                    navController.navigate("grid")   // 메인 그리드 화면으로
                },
                onBack = { navController.popBackStack() }
            )
        }

        // 3. 메인 그리드 화면
        composable("grid") {
            DiaryGridScreen(
                diaries = viewModel.diaries,
                onDiaryClick = { diaryId ->
                    viewModel.selectDiary(diaryId)
                    navController.navigate("detail")
                },
                onAddClick = {
                    navController.navigate("mood_select")
                }
            )
        }

        // 4. 상세 화면
        composable("detail") {
            DiaryDetailScreen(
                selectedDiary = viewModel.selectedDiary,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
