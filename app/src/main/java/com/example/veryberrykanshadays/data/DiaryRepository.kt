package com.example.veryberrykanshadays.data

import kotlinx.coroutines.flow.Flow

class DiaryRepository(
    private val dao: DiaryDao
) {

    fun getAllDiaries(): Flow<List<DiaryEntity>> =
        dao.getAllDiaries()

    suspend fun insertDiary(diary: DiaryEntity) {
        dao.insertDiary(diary)
    }

    suspend fun getDiaryById(id: Long): DiaryEntity? =
        dao.getDiaryById(id)
}
