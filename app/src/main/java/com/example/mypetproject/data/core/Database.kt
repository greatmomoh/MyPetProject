package com.example.mypetproject.data.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mypetproject.data.repositories.cache.SubjectsDao
import com.example.mypetproject.data.repositories.cache.models.SubjectEntity


@Database(
    entities = [
        SubjectEntity::class
    ], version = 1
)

@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun subjectsDao(): SubjectsDao
}