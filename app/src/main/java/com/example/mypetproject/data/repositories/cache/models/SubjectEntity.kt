package com.example.mypetproject.data.repositories.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "subjects")
data class SubjectEntity(
    val chapters: List<ChapterEntity>,
    val icon: String,
    @PrimaryKey
    val id: Int,
    val name: String
)