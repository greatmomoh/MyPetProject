package com.example.mypetproject.data.repositories.cache

import com.example.mypetproject.data.repositories.cache.models.LessonEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LessonsLocalRepositoryImpl @Inject constructor(private val lessonsDao: LessonsDao):LessonsLocalRepository {
    override suspend fun saveLessons(lesson: LessonEntity) {
        lessonsDao.saveLesson(lesson)
    }

    override fun getLessons(): Flow<List<LessonEntity>> {
        return lessonsDao.streamLessons()
    }

    override suspend fun clearLessons() {
        lessonsDao.deleteLessons()
    }
}