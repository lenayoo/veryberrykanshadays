package com.example.veryberrykanshadays.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.veryberrykanshadays.data.DiaryEntity.DiaryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {

    @Query("SELECT * FROM diary ORDER BY date DESC")
    fun getAllDiaries(): Flow<List<DiaryEntity>>

    @Insert
    suspend fun insertDiary(diary: DiaryEntity)

    @Query("SELECT * FROM diary WHERE id = :id LIMIT 1")
    suspend fun getDiaryById(id: Long): DiaryEntity?
}
