package com.example.veryberrykanshadays.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.veryberrykanshadays.data.DiaryEntity
import com.example.veryberrykanshadays.data.DiaryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class DiaryViewModel(
    private val repository: DiaryRepository
) : ViewModel() {

    // ① 전체 일기 리스트 (Grid 화면에서 사용)
    val diaries: StateFlow<List<DiaryEntity>> =
        repository.getAllDiaries()      // ← 리포지토리 함수 이름이 다르면 여기를 맞춰줘
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    // ② 오늘 선택한 기분 (Mood 화면)
    var currentMood by mutableStateOf("🙂")
        private set

    // ③ 상세 화면에서 보여줄 선택된 일기
    var selectedDiary by mutableStateOf<DiaryEntity?>(null)
        private set

    // -------------------------------
    // KanshaNavHost 에서 부르는 함수들
    // -------------------------------

    // MoodSelectScreen 에서 사용
    fun setMood(mood: String) {
        currentMood = mood
    }

    // DiaryInputScreen 에서 사용
    fun saveDiary(onSaved: String) {
        // 오늘 날짜(YYYY-MM-DD)
        val today = java.text.SimpleDateFormat(
            "yyyy-MM-dd",
            java.util.Locale.getDefault()
        ).format(java.util.Date())

        val diary = DiaryEntity(
            // id 는 autoGenerate 라고 가정 (DiaryEntity에서 default 값 0 으로 정의)
            date = today,
            mood = currentMood,   // currentMood 는 String
            content = onSaved
        )

        viewModelScope.launch {
            repository.insertDiary(diary)
        }
    }


    // Grid → Detail 로 이동할 때 사용
    fun selectDiary(id: Long) {
        viewModelScope.launch {
            // 리포지토리에서 id 로 1건 가져오는 함수 이름에 맞춰 수정
            selectedDiary = repository.getDiaryById(id)
        }
    }

    // ViewModel 생성용 Factory (MainActivity 에서 사용)
    companion object {
        fun provideFactory(repository: DiaryRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DiaryViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return DiaryViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
    }
}
