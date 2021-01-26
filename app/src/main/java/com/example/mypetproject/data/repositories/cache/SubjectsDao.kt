package com.example.mypetproject.data.repositories.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mypetproject.data.repositories.cache.models.SubjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SubjectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveSubjects(vararg users: SubjectEntity)

    @Query("SELECT * FROM subjects")
    abstract fun streamSubjects(): Flow<List<SubjectEntity>>

    @Query("DELETE FROM subjects")
    abstract suspend fun deleteSubjects()
}