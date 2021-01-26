package com.example.mypetproject.data.repositories

import com.example.mypetproject.model.SubjectModel
import kotlinx.coroutines.flow.Flow

interface SubjectsRepository {
    fun streamUserSubjects(refresh: Boolean = true): Flow<List<SubjectModel>>
}