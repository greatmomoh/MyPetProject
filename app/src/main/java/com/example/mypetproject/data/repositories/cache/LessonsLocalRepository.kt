package com.example.mypetproject.data.repositories.cache

import com.example.mypetproject.data.repositories.cache.models.LessonEntity
import kotlinx.coroutines.flow.Flow

interface LessonsLocalRepository {
    suspend fun saveLessons(lesson: LessonEntity)

    fun getLessons(): Flow<List<LessonEntity>>

    suspend fun clearLessons()
}