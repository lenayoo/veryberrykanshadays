package io.github.lenayoo.veryberrykanshadays.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.github.lenayoo.veryberrykanshadays.ui.screens.DiaryDetailScreen
import io.github.lenayoo.veryberrykanshadays.ui.screens.DiaryGridScreen
import io.github.lenayoo.veryberrykanshadays.ui.screens.DiaryInputScreen
import io.github.lenayoo.veryberrykanshadays.ui.screens.MoodSelectScreen
import io.github.lenayoo.veryberrykanshadays.ui.screens.WelcomeScreen
import io.github.lenayoo.veryberrykanshadays.ui.viewmodel.DiaryViewModel

@Composable
fun KanshaNavHost(
    viewModel: DiaryViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        // 0. ウェルカム画面
        composable("welcome") {
            WelcomeScreen(
                onStart = {
                    navController.navigate("grid") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }

        // 1. 오늘 기분 선택 화면
        composable("mood_select") {
            MoodSelectScreen(
                viewModel = viewModel,
                onNext = {
                    navController.navigate("input")
                },
                onHome = {
                    navController.navigate("grid") {
                        popUpTo("grid") { inclusive = true }
                    }
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
                },
                onHome = {
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
