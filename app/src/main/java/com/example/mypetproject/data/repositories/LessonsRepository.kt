package com.example.mypetproject.data.repositories

import com.example.mypetproject.model.LessonModel
import kotlinx.coroutines.flow.Flow

interface LessonsRepository {
    fun streamSavedLessons(): Flow<List<LessonModel>>

    fun saveLesson(model: LessonModel) : Flow<Unit>

}