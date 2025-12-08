package com.example.veryberrykanshadays.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        startDestination = "grid"
    ) {
        // 1. 오늘 기분 선택 화면
        composable("mood_select") {
            MoodSelectScreen(
                viewModel = viewModel,
                onNext = {
                    navController.navigate("input")
                }
            )
        }

        // 2. 감사 일기 입력 화면
        composable("input") {
            DiaryInputScreen(
                viewModel = viewModel,
                onSaved = {
                    navController.navigate("grid") {
                        popUpTo("grid") { inclusive = true }
                    }
                }
            )
        }

        // 3. 메인 그리드 화면
        composable("grid") {
            DiaryGridScreen(
                viewModel = viewModel,
                onClickDiary = { diaryId ->
                    navController.navigate("detail/$diaryId")
                },
                onAddClick = {
                    navController.navigate("mood_select")
                }
            )
        }

        // 4. 상세 화면
        composable(
            route = "detail/{diaryId}",
            arguments = listOf(navArgument("diaryId") { type = NavType.LongType })
        ) { backStackEntry ->
            val diaryId = backStackEntry.arguments?.getLong("diaryId") ?: 0L
            DiaryDetailScreen(
                id = diaryId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
