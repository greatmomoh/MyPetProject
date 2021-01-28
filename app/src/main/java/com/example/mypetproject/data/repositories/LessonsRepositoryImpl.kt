package com.example.mypetproject.data.repositories

import com.example.mypetproject.data.repositories.cache.LessonsLocalRepository
import com.example.mypetproject.model.LessonModel
import com.example.mypetproject.utils.PostExecutionThread
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonsRepositoryImpl @Inject constructor(
    private val dispatchers: PostExecutionThread,
    private val mapper: LessonMappers,
    private val lessonsLocalRepository: LessonsLocalRepository
) : LessonsRepository {
    override fun streamSavedLessons(): Flow<List<LessonModel>> {
        return lessonsLocalRepository.getLessons().map { list ->
            list.map {
                mapper.mapEntityToModel(it)
            }

        }.flowOn(dispatchers.io)
    }

    override fun saveLesson(model: LessonModel): Flow<Unit> {
        return flow {
            lessonsLocalRepository.saveLessons(mapper.mapModelToEntity(model))
            emit(Unit)
        }.flowOn(dispatchers.io)
    }
}