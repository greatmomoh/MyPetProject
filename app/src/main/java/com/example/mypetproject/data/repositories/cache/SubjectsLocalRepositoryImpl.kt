package com.example.mypetproject.data.repositories.cache

import com.example.mypetproject.data.repositories.cache.models.SubjectEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubjectsLocalRepositoryImpl @Inject constructor(private val subjectsDao: SubjectsDao) :
    SubjectsLocalRepository {
    override suspend fun saveSubjects(subject: SubjectEntity) {
        subjectsDao.saveSubjects(subject)
    }

    override fun getSubjects(): Flow<List<SubjectEntity>> {
        return subjectsDao.streamSubjects()
    }

    override suspend fun clearSubjects() {
       subjectsDao.deleteSubjects()
    }
}