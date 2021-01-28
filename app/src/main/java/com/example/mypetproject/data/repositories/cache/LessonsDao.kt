package com.example.mypetproject.data.repositories.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mypetproject.data.repositories.cache.models.LessonEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class LessonsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveLesson(vararg lesson: LessonEntity)

    @Query("SELECT * FROM lessons")
    abstract fun streamLessons(): Flow<List<LessonEntity>>

    @Query("DELETE FROM lessons")
    abstract suspend fun deleteLessons()
}