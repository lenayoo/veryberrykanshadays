
package io.github.lenayoo.veryberrykanshadays

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import io.github.lenayoo.veryberrykanshadays.data.DiaryDatabase
import io.github.lenayoo.veryberrykanshadays.data.DiaryRepository
import io.github.lenayoo.veryberrykanshadays.ui.viewmodel.DiaryViewModel
import io.github.lenayoo.veryberrykanshadays.ui.navigation.KanshaNavHost
import io.github.lenayoo.veryberrykanshadays.ui.theme.VeryberrykanshadaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // IME 입력을 위한 윈도우 설정
        WindowCompat.setDecorFitsSystemWindows(window, true)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        val db = DiaryDatabase.getInstance(applicationContext)
        val repo = DiaryRepository(db.diaryDao())
        val viewModelFactory = DiaryViewModel.provideFactory(repo)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[DiaryViewModel::class.java]

        setContent {
            VeryberrykanshadaysTheme {
                KanshaNavHost(viewModel = viewModel)
            }
        }
    }
}
