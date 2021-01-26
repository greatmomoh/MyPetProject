package com.example.mypetproject.data.repositories.cache

import com.example.mypetproject.data.repositories.cache.models.SubjectEntity
import kotlinx.coroutines.flow.Flow

interface SubjectsLocalRepository {
    suspend fun saveSubjects(subject: SubjectEntity)

    fun getSubjects(): Flow<List<SubjectEntity>>

    suspend fun clearSubjects()
}