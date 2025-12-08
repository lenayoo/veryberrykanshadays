
package com.example.veryberrykanshadays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.veryberrykanshadays.data.DiaryDatabase
import com.example.veryberrykanshadays.data.DiaryRepository
import com.example.veryberrykanshadays.ui.viewmodel.DiaryViewModel
import com.example.veryberrykanshadays.ui.navigation.KanshaNavHost
import com.example.veryberrykanshadays.ui.theme.VeryberrykanshadaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
